package com.github.koooooo7.echarts4j.chart.server;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.exception.RenderException;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.ChartUtil;
import com.github.koooooo7.echarts4j.util.JsonUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChartLiveUpdater {
    private static volatile boolean websocketSetup = false;
    private static volatile boolean liveUpdateChangeFound = false;
    private static final String DEFAULT_LIVE_UPDATE_HOST = "localhost";
    private static final int DEFAULT_LIVE_UPDATE_PORT = 9493;
    private static final String LIVE_UPDATE_HOST_CONFIG_KEY = "ECHARTS4J_CS_LIVE_UPDATE_HOST";
    private static final String LIVE_UPDATE_PORT_CONFIG_KEY = "ECHARTS4J_CS_LIVE_UPDATE_PORT";
    private static final String LIVE_UPDATE_JS =
            "var ws = new WebSocket(\"ws://%ADDR%\");\n" +
                    "\n" +
                    "ws.onopen = function(evt) { \n" +
                    "console.log(\"echarts4j live update setup!\");" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "ws.onmessage = function(evt) {\n" +
                    "const data = JSON.parse(evt.data);" +
                    "const option = data['%MY_ECHARTS%'];" +
                    "option&&%MY_ECHARTS%.setOption(JSON.parse(option));" +
                    "};\n" +
                    "\n" +
                    "\n" +
                    "ws.onclose = function(evt) {\n" +
                    "  console.log(\"Connection closed.\");\n" +
                    "};";

    public static LiveUpdatableCanvas liveUpdateBoxed(Canvas canvas) {
        if (!websocketSetup) {
            websocketSetup = true;
            setWebsocketSetup(canvas);
            canvas.getCharts().values().forEach(c -> {
                c.addJSFunction(FuncStr.of(getLiveUpdateJS()));
                c.postProcessor();
            });
        }

        if (Enhancer.isEnhanced(canvas.getClass())) {
            return (LiveUpdatableCanvas) canvas;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Canvas.class);
        enhancer.setInterfaces(new Class[]{LiveUpdatableCanvas.class});
        enhancer.setCallback(new CanvasMonitor(canvas));
        Canvas proxy = (Canvas) enhancer.create();

        return (LiveUpdatableCanvas) proxy;
    }

    private static class CanvasMonitor implements MethodInterceptor {
        public CanvasMonitor(Canvas target) {
            this.target = target;
            this.liveUpdatableWrapper = () -> target;
        }

        private final LiveUpdatableCanvas liveUpdatableWrapper;
        private volatile boolean setup = false;
        private final Canvas target;

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            if (method.getName().contains("liveUpdateChartModifier")) {
                method.invoke(liveUpdatableWrapper, args);
                return obj;
            }

            if (method.getName().contains("liveUpdateChartsModifier")) {
                method.invoke(liveUpdatableWrapper, args);
                return obj;
            }

            if (!method.getName().contains("emit")) {
                return method.invoke(target, args);
            }

            if (!setup) {
                setup = true;
                final SimpleChartServerRender render = RenderProvider.getRender(SimpleChartServerRender.class);
                render.render(target);
            }

            // try to render it to make sure it is updatable
            try (Writer wr = new StringWriter()) {
                Canvas c = (Canvas) obj;
                c.renderTo(wr);
                liveUpdateChangeFound = true;
            } catch (Exception ignore) {
                // not updatable
            }

            return null;
        }
    }

    private static void setWebsocketSetup(Canvas target) {
        Thread t = new Thread(() -> {
            final LiveSocketServer server =
                    new LiveSocketServer(new InetSocketAddress(getHost(), getPort()));
            server.start();
            while (true) {
                if (liveUpdateChangeFound) {
                    try {
                        final Map<String, String> options = new HashMap<>();
                        target.getCharts().forEach((id, c) -> {
                            options.put(ChartUtil.getFullEchartsChartId(id), c.getOptions());
                        });
                        server.broadcast(JsonUtil.writeValueAsString(options));
                    } catch (Exception ignore) {
                    } finally {
                        liveUpdateChangeFound = false;
                    }
                }
            }
        });

        t.setDaemon(true);
        t.start();
    }

    public static String getLiveUpdateJS() {
        return LIVE_UPDATE_JS.replaceFirst("%ADDR%", getHost() + ":" + getPort());
    }

    private static String getHost() {
        final String host = System.getenv(LIVE_UPDATE_HOST_CONFIG_KEY);
        if (Objects.nonNull(host)) {
            return host.trim();
        }
        return DEFAULT_LIVE_UPDATE_HOST;
    }

    private static int getPort() {
        final String port = System.getenv(LIVE_UPDATE_PORT_CONFIG_KEY);
        if (Objects.nonNull(port)) {
            return Integer.parseInt(port.trim());
        }
        return DEFAULT_LIVE_UPDATE_PORT;
    }

    static class LiveSocketServer extends WebSocketServer {

        public LiveSocketServer(InetSocketAddress address) {
            super(address);
        }

        @Override
        public void onOpen(WebSocket conn, ClientHandshake handshake) {
            // do nothing
        }

        @Override
        public void onClose(WebSocket conn, int code, String reason, boolean remote) {
            // do nothing
        }

        @Override
        public void onMessage(WebSocket conn, String message) {
            // do nothing
        }

        @Override
        public void onError(WebSocket conn, Exception ex) {
            if (conn != null) {
                conn.close();
            }
            throw new RenderException(ex);
        }

        @Override
        public void onStart() {
            setConnectionLostTimeout(150);
        }

    }
}

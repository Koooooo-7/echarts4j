package com.github.koooooo7.echarts4j.chart.server;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.type.FuncStr;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.function.Consumer;

public class DynamicChartLiveUpdater {
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
                    "ws.onmessage = function(evt) {\n" +
                    " %MY_ECHARTS%.setOption(JSON.parse(evt.data));" +
                    "};\n" +
                    "\n" +
                    "ws.onclose = function(evt) {\n" +
                    "  console.log(\"Connection closed.\");\n" +
                    "}; ";

    public static Canvas liveUpdateBoxed(Canvas canvas, Consumer<Canvas> updater) {
        if (!websocketSetup) {
            websocketSetup = true;
            setWebsocketSetup(canvas);
            canvas.getCharts().values().forEach(c -> {
                c.addJSFunction(FuncStr.of(getLiveUpdateJS()));
                c.postProcessor();
            });
        }

        if (Enhancer.isEnhanced(canvas.getClass())) {
            return canvas;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Canvas.class);
        enhancer.setCallback(new CanvasMonitor(canvas));
        Canvas proxy = (Canvas) enhancer.create();
        updater.accept(proxy);

        return proxy;
    }

    private static class CanvasMonitor implements MethodInterceptor {
        public CanvasMonitor(Canvas target) {
            this.target = target;
        }

        private final Canvas target;

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            // skip the render call
            if (method.getName().contains("render")) {
                return method.invoke(target, args);
            }

            // try to render it to make sure it is updatable
            try (Writer wr = new StringWriter()) {
                Canvas c = (Canvas) obj;
                c.renderTo(wr);
                liveUpdateChangeFound = true;
            } catch (Exception ignore) {
                // not updatable
            }

            return method.invoke(target, args);
        }
    }

    private static void setWebsocketSetup(Canvas target) {
        Thread t = new Thread(() -> {
            final LiveSocketServer server =
                    new LiveSocketServer(new InetSocketAddress(getHost(), getPort()));
            server.start();
            while (true) {
                if (liveUpdateChangeFound) {
                    target.getCharts().values().stream()
                            .findFirst().ifPresent(s -> {
                                server.broadcast(s.getOptions());
                            });
                    liveUpdateChangeFound = false;
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
}

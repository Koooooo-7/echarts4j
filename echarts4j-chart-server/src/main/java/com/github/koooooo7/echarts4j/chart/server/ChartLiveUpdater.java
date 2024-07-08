package com.github.koooooo7.echarts4j.chart.server;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.exception.RenderException;
import com.github.koooooo7.echarts4j.type.FuncStr;
import com.github.koooooo7.echarts4j.util.ChartUtil;
import com.github.koooooo7.echarts4j.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ChartLiveUpdater {
    private ChartLiveUpdater() {
    }

    // global shared lock cross Canvas
    private static final Object UPDATE_LOCK = new Object();
    private static final Object WEB_SOCKET_SETUP_LOCK = new Object();
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
            synchronized (WEB_SOCKET_SETUP_LOCK) {
                setWebsocketSetup(canvas);
                websocketSetup = true;
            }
            canvas
                    .asBuilder()
                    .updateCharts((id, chart) -> chart.addJSFunction(FuncStr.of(getLiveUpdateJS())))
                    .build();
        }

        return new LiveUpdatableCanvas() {
            private volatile boolean setup = false;

            @Override
            public Canvas getTarget() {
                return canvas;
            }

            @Override
            public void emit() {
                synchronized (UPDATE_LOCK) {
                    if (!setup) {
                        canvas.render();
                        setup = true;
                    }

                    // render it to make sure it is updatable and sync the latest status
                    try {
                        canvas.render();
                        liveUpdateChangeFound = true;
                    } catch (Exception ignore) {
                        // not updatable
                    }
                }
            }
        };
    }

    private static void setWebsocketSetup(Canvas target) {
        if (websocketSetup) {
            return;
        }
        Thread t = new Thread(() -> {
            final LiveSocketServer server =
                    new LiveSocketServer(new InetSocketAddress(getHost(), getPort()));
            server.start();
            while (true) {
                if (liveUpdateChangeFound) {
                    synchronized (UPDATE_LOCK) {
                        try {
                            final Map<String, String> options = new HashMap<>();
                            target.getCharts()
                                    .forEach((id, chart) -> options.put(ChartUtil.getFullEchartsChartId(id), chart.getOptions()));
                            server.broadcast(JsonUtil.writeValueAsString(options));
                        } catch (Exception ignore) {
                        } finally {
                            liveUpdateChangeFound = false;
                        }
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

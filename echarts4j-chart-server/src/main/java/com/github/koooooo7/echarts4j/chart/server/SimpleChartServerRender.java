package com.github.koooooo7.echarts4j.chart.server;

import com.github.koooooo7.echarts4j.chart.Canvas;
import com.github.koooooo7.echarts4j.exception.RenderException;
import com.github.koooooo7.echarts4j.render.DefaultRender;
import com.github.koooooo7.echarts4j.render.Render;
import com.github.koooooo7.echarts4j.render.RenderProvider;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class SimpleChartServerRender implements Render {
    private static volatile boolean setup = false;
    private static final Object SERVER_SETUP_LOCK = new Object();
    // not thread safe
    private static Writer writer;
    private static final String DEFAULT_SERVER_PORT_CONFIG_KEY = "ECHARTS4J_CHART_SERVER_PORT";
    private static final int DEFAULT_SERVER_PORT = 9394;


    @Override
    public void render(Canvas canvas) {
        try (Writer writer = new StringWriter()) {
            RenderProvider.getRender(DefaultRender.class).render(canvas, writer);
            refreshServerCharts(writer);
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }

    @Override
    public void render(Canvas canvas, Writer writer) {
        render(canvas);
    }

    @Override
    public void render(Canvas canvas, File file) {
        render(canvas);
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE - 1;
    }

    private void refreshServerCharts(Writer wr) {
        writer = wr;
        try {
            if (!setup) {
                synchronized (SERVER_SETUP_LOCK) {
                    if (setup) {
                        return;
                    }
                    final Thread t = new Thread(() -> {
                        try {
                            HttpServer server;
                            server = HttpServer.create(new InetSocketAddress(getPort()), 0);
                            server.createContext("/", new ChartServerHandler(() -> writer));
                            server.setExecutor(Executors.newFixedThreadPool(10));
                            server.start();
                            System.out.println("SimplyChartServer listen on port: " + getPort());
                        } catch (IOException e) {
                            throw new RenderException(e);
                        }
                    });
                    t.setDaemon(true);
                    t.start();
                    setup = true;
                }

            }
        } catch (Exception e) {
            throw new RenderException(e);
        }
    }


    private static class ChartServerHandler implements HttpHandler {
        public ChartServerHandler(Supplier<Writer> respWriter) {
            this.respWriter = respWriter;
        }

        private final Supplier<Writer> respWriter;

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            String response = respWriter.get().toString();
            os.write(response.getBytes());
            os.close();
        }
    }


    private static int getPort() {
        final String port = System.getenv(DEFAULT_SERVER_PORT_CONFIG_KEY);
        if (Objects.nonNull(port)) {
            return Integer.parseInt(port.trim());
        }
        return DEFAULT_SERVER_PORT;
    }

    /**
     * A handy method to block the main process exit
     */
    public static void on() {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            latch.await();
        } catch (InterruptedException ignore) {
        }
    }
}

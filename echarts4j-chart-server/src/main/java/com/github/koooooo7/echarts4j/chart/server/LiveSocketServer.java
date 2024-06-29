package com.github.koooooo7.echarts4j.chart.server;


import com.github.koooooo7.echarts4j.exception.RenderException;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class LiveSocketServer extends WebSocketServer {

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

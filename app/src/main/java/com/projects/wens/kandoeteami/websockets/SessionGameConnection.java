package com.projects.wens.kandoeteami.websockets;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import de.roderick.weberknecht.WebSocket;
import de.roderick.weberknecht.WebSocketEventHandler;
import de.roderick.weberknecht.WebSocketMessage;

public class SessionGameConnection extends Thread implements Runnable {
    public final String websocketConnection = "wss://wildfly-teamiip2kdgbe.rhcloud.com/circleSession";
    WebSocket websocket;

    public SessionGameConnection() {
    }

    public void run() {

        Map<String, String> headersSetup = new HashMap<String, String>();
        headersSetup.put("heart-beat","10000");
        Stomp stomp = new Stomp(websocketConnection, headersSetup, new ListenerWSNetwork() {
            @Override
            public void onState(int state) {
            }
        });

        stomp.connect();
        System.out.println("Stomp connect finished.");
        stomp.subscribe(new Subscription("ws://wildfly-teamiip2kdgbe.rhcloud.com/topic/move", new ListenerSubscription() {
            @Override
            public void onMessage(Map<String, String> headers, String body) {
                System.out.println("Message Received");
            }
        }));

        /*try {
            URI url = new URI("ws://wildfly-teamiip2kdgbe.rhcloud.com/circleSession");
            websocket = new WebSocket(url);

            websocket.setEventHandler(new WebSocketEventHandler() {
                @Override
                public void onOpen() {
                    System.out.println("Sockets open");
                }

                @Override
                public void onMessage(WebSocketMessage webSocketMessage) {
                    System.out.println("Message recieved: " + webSocketMessage);
                }

                @Override
                public void onError(IOException e) {
                    System.out.println("Socket error");
                }

                @Override
                public void onClose() {
                    System.out.println("Socket close");
                }

                @Override
                public void onPing() {
                    System.out.println("Socket ping");
                }

                @Override
                public void onPong() {
                    System.out.println("Socket pong");
                }
            });

            websocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
    }

    public void finish() {
        websocket.close();
    }

    public <T> void send(String dest, T message) {
        websocket.send("testMessage from android webSocket");
    }
}

package com.projects.wens.kandoeteami.websockets;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by senne on 17/03/2016.
 */
public class SessionGameConnection extends Thread implements Runnable {
    public final String websocketConnection="ws://wildfly-teamiip2kdgbe.rhcloud.com/circleSession";
    Stomp client;
    Map<String, String> headerSetup = new HashMap<>();
    HashMap<String, ListenerSubscription> subscriptions;
    String cookie;

    boolean active;

    public SessionGameConnection(HashMap<String,ListenerSubscription> subscriptions, String cookie){
        this.subscriptions = subscriptions;
        this.cookie = cookie;
        this.active = false;
    }

    public void run(){
        if (active){
            headerSetup.put("Cookie", cookie);
            Log.i("connect", "going to connect to stomp with cookie");
            Log.i("connect", "going to connect stomp");
        }

        headerSetup.put("Cookie", cookie);
        Log.i("connect", "going to connect to stomp with cookie");
        Log.i("connect", "going to connect stomp");

        client = new Stomp(websocketConnection, headerSetup, new ListenerWSNetwork() {
            @Override
            public void onState(int state) {
                Log.i("State :", Integer.toString(state));
            }
        });

        client.connect();

        Log.i("connected", "True");

        for (Map.Entry<String, ListenerSubscription> entry : subscriptions.entrySet()){
            client.subscribe(new Subscription(entry.getKey(), entry.getValue()));
        }
        send("","test");
    }

    public void finish(){
        active = false;
        client.disconnect();
    }

    public <T> void send(String dest, T message){
        String m = new Gson().toJson(message);
        Log.i("Stomp message", m);
        this.client.send(dest, null, m);
    }
}

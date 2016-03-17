package com.projects.wens.kandoeteami.websockets;

import java.util.Map;

public interface ListenerSubscription {
    public void onMessage(Map<String, String> headers, String body);
}

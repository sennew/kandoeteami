package com.projects.wens.kandoeteami.websockets;

import java.util.Map;

public interface ListenerSubscription {
    /**
     * on message received
     * @param headers
     * @param body
     */
    public void onMessage(Map<String, String> headers, String body);
}

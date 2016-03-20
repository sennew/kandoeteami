package com.projects.wens.kandoeteami.session.data;

public class CurrentMove {
    private String token;
    private Integer sessionId;
    private Integer cardId;

    public CurrentMove() {
    }

    public CurrentMove(String token, Integer sessionId, Integer cardId) {
        this.token = token;
        this.sessionId = sessionId;
        this.cardId = cardId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}

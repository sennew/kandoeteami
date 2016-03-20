package com.projects.wens.kandoeteami.session.data;

import com.projects.wens.kandoeteami.subthemes.data.SubTheme;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.projects.wens.kandoeteami.themes.data.Theme;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.List;

public class SessionDTO {
    private int sessionId;
    private String sessionName;
    private SessionMode mode;
    private SessionType type;
    private SessionState state;
    private int minCards;
    private int maxCards;
    private int size;
    private String startTime;
    private String endTime;
    private boolean userAddCards;
    private int themeId;
    private int subThemeId;
    private boolean chosenCards;
    private List<Card> cards;
    private List<User> users;
    private Theme theme;
    private SubTheme subTheme;
    private int playtime;

    public SessionDTO() {
    }

    //Constructor voor te testen
    public SessionDTO(int sessionId, String sessionName){
        this.sessionId = sessionId;
        this.sessionName = sessionName;

    }

    public int getSubThemeId() {
        return subThemeId;
    }

    public void setSubThemeId(int subThemeId) {
        this.subThemeId = subThemeId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public SubTheme getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(SubTheme subTheme) {
        this.subTheme = subTheme;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public SessionMode getMode() {
        return mode;
    }

    public void setMode(SessionMode mode) {
        this.mode = mode;
    }

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public int getMinCards() {
        return minCards;
    }

    public void setMinCards(int minCards) {
        this.minCards = minCards;
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setMaxCards(int maxCards) {
        this.maxCards = maxCards;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isUserAddCards() {
        return userAddCards;
    }

    public void setUserAddCards(boolean userAddCards) {
        this.userAddCards = userAddCards;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public boolean isChosenCards() {
        return chosenCards;
    }

    public void setChosenCards(boolean chosenCards) {
        this.chosenCards = chosenCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

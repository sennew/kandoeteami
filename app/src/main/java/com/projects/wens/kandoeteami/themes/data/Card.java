package com.projects.wens.kandoeteami.themes.data;

/**
 * Created by senne on 11/03/2016.
 */
public class Card {
    private int cardId;
    private String description;
    private String imageURL;

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }
}

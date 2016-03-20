package com.projects.wens.kandoeteami.subthemes.data;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public class SubTheme {
    private int subThemeId;
    private String subThemeName;
    private String description;
    private String iconURL;
    private Organisation organisation;
    private List<Card> cards;
    private Theme theme;


    //CONSTRUCTOR OM TE TESTEN
    public SubTheme(int subThemeId, String subThemeName){
        this.subThemeId = subThemeId;
        this.subThemeName = subThemeName;
    }

    public String getThemeName() {
        return subThemeName;
    }

    public void setThemeName(String themeName) {
        this.subThemeName = themeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getSubThemeId() {

        return subThemeId;
    }

    public void setThemeId(int themeId) {
        this.subThemeId = themeId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

}

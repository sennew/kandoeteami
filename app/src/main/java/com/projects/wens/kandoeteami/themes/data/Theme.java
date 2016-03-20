package com.projects.wens.kandoeteami.themes.data;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

/**
 * Created by michaelkees on 01/03/16.
 */
public class Theme {
    private int themeId;
    private String themeName;
    private String description;
    private String iconURL;
    private Organisation organisation;
    private List<Card> cards;
    private List<SubTheme> subThemes;

    private int countSubthemes;

    public Theme() {
    }

    //CONSTRUCTOR VOOR TESTING
    public Theme(String themeName, int themeId){
        this.themeId = themeId;
        this.themeName = themeName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
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

    public int getCountSubthemes() {
        return countSubthemes;
    }

    public void setCountSubthemes(int countSubthemes) {
        this.countSubthemes = countSubthemes;
    }


    public List<SubTheme> getSubThemes() {
        return subThemes;
    }

    public void setSubThemes(List<SubTheme> subThemes) {
        this.subThemes = subThemes;
    }

}

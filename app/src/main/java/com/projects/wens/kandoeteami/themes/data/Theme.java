package com.projects.wens.kandoeteami.themes.data;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

/**
 * Created by michaelkees on 01/03/16.
 */
public class Theme {
    private int themeId;
    private String themeName;
    private String description;
    private String iconURL;
    private Organisation organisation;

    public Theme() {
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
}

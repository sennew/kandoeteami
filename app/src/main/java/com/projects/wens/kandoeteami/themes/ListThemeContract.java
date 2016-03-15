package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

/**
 * Created by michaelkees on 01/03/16.
 */
public interface ListThemeContract {

    interface View {
        void setProgressIndicator(boolean active);
        void showThemes(List<Theme> themes);
        void showThemeDetail(Integer themeId);
        void showSubThemes(Integer themeId);
        void showErrorMessage(int status);

    }

    interface UserActionListener {
        void loadThemes(String token);
        void loadThemesForOrganisation(String token, int organisationId);
        void loadSubThemesForTheme(int themeId);
        void openThemeDetail(Theme clickTheme);
    }
}

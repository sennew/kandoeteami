package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

/**
 * Created by michaelkees on 01/03/16.
 */
public interface ListThemeContract {

    interface View {
        /**
         * show progress on loading themes
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         * show themes in view with param themes
         * @param themes
         */
        void showThemes(List<Theme> themes);

        /**
         * show theme detail activity
         * @param themeId
         */
        void showThemeDetail(Integer themeId);

        /**
         * show subthemes of theme (with themeid)
         * @param themeId
         */
        void showSubThemes(Integer themeId);

        /**
         * show error message on failure rest call
         * @param status
         */
        void showErrorMessage(int status);

    }

    interface UserActionListener {

        /**
         * request rest call GET themes with authorization token
         * @param token
         */
        void loadThemes(String token);

        /**
         * request rest call GET themes of organisation with token and orga Id
         * @param token
         * @param organisationId
         */
        void loadThemesForOrganisation(String token, int organisationId);

        /**
         * request rest call load subthemes of theme with orga Id
         * @param themeId
         */
        void loadSubThemesForTheme(int themeId);

        /**
         * request open theme detail activity
         * @param clickTheme
         */
        void openThemeDetail(Theme clickTheme);
    }
}

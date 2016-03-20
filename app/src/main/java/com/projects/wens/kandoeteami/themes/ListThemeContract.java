package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

/**
 * Created by michaelkees on 01/03/16.
 */
public interface ListThemeContract {

    interface View {
        /**
         *
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         *
         * @param themes
         */
        void showThemes(List<Theme> themes);

        /**
         *
         * @param themeId
         */
        void showThemeDetail(Integer themeId);

        /**
         *
         * @param themeId
         */
        void showSubThemes(Integer themeId);

        /**
         *
         * @param status
         */
        void showErrorMessage(int status);

    }

    interface UserActionListener {

        /**
         *
         * @param token
         */
        void loadThemes(String token);

        /**
         *
         * @param token
         * @param organisationId
         */
        void loadThemesForOrganisation(String token, int organisationId);

        /**
         *
         * @param themeId
         */
        void loadSubThemesForTheme(int themeId);

        /**
         *
         * @param clickTheme
         */
        void openThemeDetail(Theme clickTheme);
    }
}

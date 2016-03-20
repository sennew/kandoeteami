package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

public interface ListSubThemeContract {
    interface View{
        /**
         * show progress loading subthemes
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         * show subthemes in view with param themes
         * @param themes
         */
        void showSubThemes(List<SubTheme> themes);

        /**
         * show subthemedetail activity with param id
         * @param subThemeId
         */
        void showSubThemeDetail(Integer subThemeId);

        /**
         * show error message on failure rest call
         * @param status
         */
        void showErrorMessage(int status);
    }

    interface UserActionListener{

        /**
         * request rest call GET subthemes with token
         * @param token
         */
        void loadSubthemes(String token);

        /**
         * request rest call Get subthemes of theme with themeId
         * @param token
         * @param themeId
         */
        void loadSubthemesForTheme(String token, int themeId);

        /**
         * request open sub theme detail activity
         * @param clickTheme
         */
        void openSubThemeDetail(SubTheme clickTheme);
    }
}



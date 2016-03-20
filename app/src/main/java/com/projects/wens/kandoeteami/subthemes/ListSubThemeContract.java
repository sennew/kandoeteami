package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public interface ListSubThemeContract {
    interface View{
        /**
         *
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         *
         * @param themes
         */
        void showSubThemes(List<SubTheme> themes);

        /**
         *
         * @param subThemeId
         */
        void showSubThemeDetail(Integer subThemeId);

        /**
         *
         * @param status
         */
        void showErrorMessage(int status);
    }

    interface UserActionListener{

        /**
         *
         * @param token
         */
        void loadSubthemes(String token);

        /**
         *
         * @param token
         * @param themeId
         */
        void loadSubthemesForTheme(String token, int themeId);

        /**
         *
         * @param clickTheme
         */
        void openSubThemeDetail(SubTheme clickTheme);
    }
}



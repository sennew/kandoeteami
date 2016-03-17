package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public interface ListSubThemeContract {
    interface View{
        void setProgressIndicator(boolean active);
        void showSubThemes(List<SubTheme> themes);
        void showSubThemeDetail(Integer subThemeId);
        void showErrorMessage(int status);
    }

    interface UserActionListener{
        void loadSubthemes(String token);
        void loadSubthemesForTheme(String token, int themeId);
        void openSubThemeDetail(SubTheme clickTheme);
    }
}



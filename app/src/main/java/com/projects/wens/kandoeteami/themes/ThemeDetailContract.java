package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

/**
 * Created by senne on 10/03/2016.
 */
public interface ThemeDetailContract {
    interface View{
        /**
         *
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         *
         * @param theme
         * @param items
         * @param activeSession
         * @param countSession
         */
        void showTheme(Theme theme, List<GroupItem> items, int activeSession, int countSession);

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener {

        /**
         *
         * @param token
         * @param id
         */
        void loadTheme(String token, int id);
    }
}

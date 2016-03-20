package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

public interface ThemeDetailContract {
    interface View{
        /**
         * show succes message on success rest call
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         * show theme in view on success rest call with theme, group items, active sessions and count
         * @param theme
         * @param items
         * @param activeSession
         * @param countSession
         */
        void showTheme(Theme theme, List<GroupItem> items, int activeSession, int countSession);

        /**
         * show error message on failure rest call
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener {

        /**
         * request rest call GET theme with token and id
         * @param token
         * @param id
         */
        void loadTheme(String token, int id);
    }
}

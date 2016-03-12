package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

/**
 * Created by senne on 10/03/2016.
 */
public interface ThemeDetailContract {
    interface View{
        void showSuccesMessage(String message);
        void showTheme(Theme theme, List<GroupItem> items);
        void showErrorMessage(String message);
    }

    interface UserActionListener {
        void loadTheme(String token, int id);
    }
}

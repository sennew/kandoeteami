package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public interface SubThemeDetailContract {
    interface View{
        void showSuccesMessage(String message);
        void showSubTheme(SubTheme subTheme, List<GroupItem> items,int activeSession, int coutSession );
        void showErrorMessage(String message);
    }

    interface UserActionListener{
        void loadSubTheme(String token, int id);
    }
}

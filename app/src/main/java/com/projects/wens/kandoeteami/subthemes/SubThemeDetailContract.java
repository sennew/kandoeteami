package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public interface SubThemeDetailContract {
    interface View{
        /**
         * show success message on success rest call
         * @param message
         */
        void showSuccessMessage(String message);

        /**
         * show subtheme in view with param subtheme, items, active and total sessions
         * @param subTheme
         * @param items
         * @param activeSession
         * @param coutSession
         */
        void showSubTheme(SubTheme subTheme, List<GroupItem> items,int activeSession, int coutSession );

        /**
         * show error message on failure rest call
         * @param message
         */
        void showErrorMessage(String message);

        void showSuccesMessage(String s);
    }

    interface UserActionListener{

        /**
         * request loading subtheme rest call GET
         * @param token
         * @param id
         */
        void loadSubTheme(String token, int id);
    }
}

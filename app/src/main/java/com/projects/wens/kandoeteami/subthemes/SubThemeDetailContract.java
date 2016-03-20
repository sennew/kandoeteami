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
         *
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         *
         * @param subTheme
         * @param items
         * @param activeSession
         * @param coutSession
         */
        void showSubTheme(SubTheme subTheme, List<GroupItem> items,int activeSession, int coutSession );

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener{

        /**
         *
         * @param token
         * @param id
         */
        void loadSubTheme(String token, int id);
    }
}

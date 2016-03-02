package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.retrofit.service.ThemeService;

/**
 * Created by michaelkees on 01/03/16.
 */
public class ListThemePresenter implements ListThemeContract.UserActionListener {

    private ThemeService service;

    private ListThemeContract.View view;

    public ListThemePresenter(ThemeService service, ListThemeContract.View view) {
        this.service = service;
        this.view = view;
    }

}

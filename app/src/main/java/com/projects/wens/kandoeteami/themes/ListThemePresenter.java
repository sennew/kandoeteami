package com.projects.wens.kandoeteami.themes;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListThemePresenter implements ListThemeContract.UserActionListener {

    private ThemeService service;

    private ListThemeContract.View view;

    public ListThemePresenter(ThemeService service, ListThemeContract.View view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void loadThemes(String token) {
        if (view != null){
            view.setProgressIndicator(true);
        }
        service.getThemes("Bearer " + token, new Callback<List<Theme>>() {
            @Override
            public void success(List<Theme> themes, Response response) {
                if (view != null) {
                    view.setProgressIndicator(false);
                    view.showThemes(themes);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getResponse().getStatus());
            }
        });
    }

    @Override
    public void openThemeDetail(Theme clickTheme) {
        view.showThemeDetail(clickTheme.getThemeId());
    }
}

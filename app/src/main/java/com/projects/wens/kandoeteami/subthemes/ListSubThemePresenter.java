package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.retrofit.service.SubThemaService;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListSubThemePresenter implements ListSubThemeContract.UserActionListener {
    private SubThemaService service;
    private ListSubThemeContract.View view;
    public ListSubThemePresenter(SubThemaService service, ListSubThemeContract.View view){
        this.service = service;
        this.view = view;
    }

    public void loadSubthemes(String token) {
        if (view != null){
            view.setProgressIndicator(true);
        }

        service.getSubThemes("Bearer " +token, new Callback<List<SubTheme>>() {
            @Override
            public void success(List<SubTheme> themes, Response response) {
                if (view != null){
                    view.setProgressIndicator(false);
                    view.showSubThemes(themes);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getResponse().getStatus());
            }
        });
    }

    @Override
    public void loadSubthemesForTheme(String token, int themeId) {
        service.getSubthemesByThemeId("Bearer " + token, themeId, new Callback<List<SubTheme>>() {
            @Override
            public void success(List<SubTheme> subThemes, Response response) {
                if (view != null){
                    view.setProgressIndicator(false);
                    view.showSubThemes(subThemes);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getResponse().getStatus());
            }
        });
    }

    @Override
    public void openSubThemeDetail(SubTheme clickTheme) {
        view.showSubThemeDetail(clickTheme.getSubThemeId());
    }
}

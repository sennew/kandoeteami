package com.projects.wens.kandoeteami.theme;

import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.themes.ThemeDetailContract;
import com.projects.wens.kandoeteami.themes.ThemeDetailPresenter;
import com.projects.wens.kandoeteami.themes.data.Theme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit.Callback;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class ThemePresenterTest {
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";
    private static final Theme THEME = new Theme("TEST THEME", 1);

    private ThemeDetailPresenter presenter;
    @Mock
    private ThemeDetailContract.View view;
    @Mock
    private ThemeService service;
    @Captor
    private ArgumentCaptor<Callback<Theme>> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ThemeDetailPresenter(view, service);
    }

    @Test
    public void testShouldLoadTheme() throws Exception {
        presenter.loadTheme(TOKEN,THEME.getThemeId());
        verify(service).getTheme(anyString(),anyInt(),argumentCaptor.capture());
        argumentCaptor.getValue().success(THEME, null);
        verify(view).showSuccesMessage("Successfully loaded theme");
    }
}

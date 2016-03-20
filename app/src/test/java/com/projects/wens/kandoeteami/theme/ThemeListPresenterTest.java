package com.projects.wens.kandoeteami.theme;

import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.themes.ListThemeContract;
import com.projects.wens.kandoeteami.themes.ListThemePresenter;
import com.projects.wens.kandoeteami.themes.data.Theme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import retrofit.Callback;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by senne on 20/03/2016.
 */
public class ThemeListPresenterTest {
    private static List<Theme> THEMES = Lists.newArrayList(new Theme("TEST 1", 1), new Theme("TEST 2", 2));
    private static String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";
    private ListThemePresenter mThemePresenter;
    @Mock
    private ListThemeContract.View mListView;
    @Mock
    private ThemeService mThemeService;
    @Captor
    private ArgumentCaptor<Callback<List<Theme>>> mLoadThemeCallbackCaptor;


    @Before
    public void setupListThemePresenter(){
        MockitoAnnotations.initMocks(this);
        mThemePresenter = new ListThemePresenter(mThemeService, mListView);
    }

    @Test
    public void loadThemesFromService_AndLoadIntoView(){
        mThemePresenter.loadThemes(TOKEN);
        verify(mThemeService).getThemes(anyString(), mLoadThemeCallbackCaptor.capture());
        mLoadThemeCallbackCaptor.getValue().success(THEMES, null);
        verify(mListView).setProgressIndicator(false);
        verify(mListView).showThemes(THEMES);
    }

    @Test
    public void loadThemesForOrganisationFromService_AndLoadIntoView(){
        mThemePresenter.loadThemesForOrganisation(TOKEN, 1);
        verify(mThemeService).getThemesOfOrganisation(anyString(), anyInt(), mLoadThemeCallbackCaptor.capture());
        mLoadThemeCallbackCaptor.getValue().success(THEMES, null);
        verify(mListView).setProgressIndicator(false);
        verify(mListView).showThemes(THEMES);
    }

    @Test
    public void clickOnSubthemes_ShowsSubthemes(){
        mThemePresenter.loadSubThemesForTheme(1);
        verify(mListView).showSubThemes(1);
    }

    @Test
    public void clickOnTheme_ShowsThemeDetailUi(){
        Theme theme = THEMES.get(1);
        mThemePresenter.openThemeDetail(theme);
        verify(mListView).showThemeDetail(theme.getThemeId());
    }



}

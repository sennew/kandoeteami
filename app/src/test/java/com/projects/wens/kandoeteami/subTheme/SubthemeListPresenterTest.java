package com.projects.wens.kandoeteami.subTheme;

import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.projects.wens.kandoeteami.retrofit.service.SubThemaService;
import com.projects.wens.kandoeteami.subthemes.ListSubThemeContract;
import com.projects.wens.kandoeteami.subthemes.ListSubThemePresenter;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

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
public class SubthemeListPresenterTest {
    private static List<SubTheme> SUBTHEMES = Lists.newArrayList(new SubTheme(1, "SUBTHEME 1"), new SubTheme(2, "SUBTHEME 2"));
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";

    private ListSubThemePresenter mSubThemePresenter;
    @Mock
    private ListSubThemeContract.View mListView;
    @Mock
    private SubThemaService mSubThemeService;
    @Captor
    private ArgumentCaptor<Callback<List<SubTheme>>> mLoadSubThemeCallbackCaptor;

    @Before
    public void setupListSubThemePresenter(){
        MockitoAnnotations.initMocks(this);
        mSubThemePresenter = new ListSubThemePresenter(mSubThemeService,mListView);
    }

    @Test
    public void loadSubthemesFromServiceAndLoadIntoView(){
        mSubThemePresenter.loadSubthemes(TOKEN);
        verify(mSubThemeService).getSubThemes(anyString(), mLoadSubThemeCallbackCaptor.capture());
        mLoadSubThemeCallbackCaptor.getValue().success(SUBTHEMES, null);
        verify(mListView).setProgressIndicator(false);
        verify(mListView).showSubThemes(SUBTHEMES);
    }

    @Test
    public void loadSubthemesForThemeFromServiceAndLoadIntoView(){
        mSubThemePresenter.loadSubthemesForTheme(TOKEN, 1);
        verify(mSubThemeService).getSubthemesByThemeId(anyString(), anyInt(), mLoadSubThemeCallbackCaptor.capture());
        mLoadSubThemeCallbackCaptor.getValue().success(SUBTHEMES,null);
        verify(mListView).setProgressIndicator(false);
        verify(mListView).showSubThemes(SUBTHEMES);
    }

    @Test
    public void clickOnSubtheme_showsDetailUi(){
        SubTheme sub = SUBTHEMES.get(1);
        mSubThemePresenter.openSubThemeDetail(sub);
        verify(mListView).showSubThemeDetail(sub.getSubThemeId());
    }

}

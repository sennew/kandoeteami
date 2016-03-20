package com.projects.wens.kandoeteami.subTheme;

import com.projects.wens.kandoeteami.retrofit.service.SubThemaService;
import com.projects.wens.kandoeteami.subthemes.SubThemeDetailContract;
import com.projects.wens.kandoeteami.subthemes.SubThemeDetailPresenter;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

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

public class SubthemePresenterTest {
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";
    private static final SubTheme SUBTHEME = new SubTheme(1,"TEST SUBTHEME");

    private SubThemeDetailPresenter presenter;

    @Mock
    private SubThemeDetailContract.View view;
    @Mock
    private SubThemaService service;
    @Captor
    private ArgumentCaptor<Callback<SubTheme>> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SubThemeDetailPresenter(view,service);
    }

    @Test
    public void testShouldLoadSubtheme() throws Exception {
        presenter.loadSubTheme(TOKEN, SUBTHEME.getSubThemeId());
        verify(service).getSubTheme(anyString(),anyInt(),argumentCaptor.capture());
        argumentCaptor.getValue().success(SUBTHEME, null);
        verify(view).showSuccesMessage("Succesfully loaded subtheme");
    }

}

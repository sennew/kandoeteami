package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

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

public class SessionDetailPresenterTest {
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";
    private static final SessionDTO SESSION = new SessionDTO(1,"TEST SESSION 1");

    private SessionDetailPresenter presenter;

    @Mock
    private SessionDetailContract.View view;
    @Mock
    private SessionService service;
    @Captor
    private ArgumentCaptor<Callback<SessionDTO>> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SessionDetailPresenter(view,service);
    }

    @Test
    public void testShouldLoadDetailSession() {
        presenter.loadSession(TOKEN, SESSION.getSessionId());
        verify(service).getSessionById(anyString(), anyInt(), argumentCaptor.capture());
        argumentCaptor.getValue().success(SESSION, null);
        verify(view).showSuccesMessage("Successfully loaded session");
    }
}

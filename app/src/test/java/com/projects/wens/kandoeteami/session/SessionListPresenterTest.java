package com.projects.wens.kandoeteami.session;

import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.user.data.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import retrofit.Callback;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by senne on 20/03/2016.
 */
public class SessionListPresenterTest {
    private static final List<User> USERS = Lists.newArrayList(new User(), new User());
    private static List<SessionDTO> SESSIONS = Lists.newArrayList(new SessionDTO(1, "TEST SESSION 1"), new SessionDTO(2, "TEST SESSION 2"));
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";

    private ListSessionPresenter mListSessionPresenter;

    @Mock
    private ListSessionContract.view view;
    @Mock
    private SessionService service;
    @Captor
    private ArgumentCaptor<Callback<List<SessionDTO>>> mLoadSessionOrganisationCallbackCaptor;

    @Before
    public void setupListSessionPresenter(){
        MockitoAnnotations.initMocks(this);
        mListSessionPresenter = new ListSessionPresenter(view,service);
    }

    @Test
    public void loadListSessionFromServiceIntoView(){
        mListSessionPresenter.loadSessions(true, TOKEN);
        verify(service).getSessions(anyString(), mLoadSessionOrganisationCallbackCaptor.capture());
        mLoadSessionOrganisationCallbackCaptor.getValue().success(SESSIONS, null);
        verify(view).setProgressIndicator(false);
        verify(view).showSessions(SESSIONS);
    }

    @Test
    public void clickOnSessionDetailButton_showDetailUi(){
        SessionDTO session = SESSIONS.get(1);
        mListSessionPresenter.openSessionDetail(session);
        verify(view).showSessionDetail(session.getSessionId());
    }

    @Test
    public void clickOnSession_showGame(){
        SessionDTO session = SESSIONS.get(1);
        mListSessionPresenter.openSessionGame(session);
        verify(view).showSessionGame(session.getSessionId());
    }
}

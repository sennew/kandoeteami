package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.User;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by michaelkees on 03/03/16.
 */
public class ChangeUserPasswordPresenterTest {
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";

    @Mock
    private UserService service;

    @Mock
    private ChangeUserPasswordContract.View view;

    private ChangeUserPasswordPresenter presenter;

    @Captor
    private ArgumentCaptor<Callback<String>> stringCallbackCaptop;

    @Captor
    private ArgumentCaptor<Callback<User>> userCallbackCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ChangeUserPasswordPresenter(service, view);
    }

    @Test
    public void testShouldLoadUser(){
        presenter.loadUser(TOKEN);

        verify(service).getCurrentUser(anyString(), userCallbackCaptor.capture());

        User t = new User();
        userCallbackCaptor.getValue().success(t, null);
        verify(view).verifyUser(t);
    }

    @Test
    public void testShouldUpdatePassword() throws Exception {
        User user = new User(1, "ArneLauryssens", "test123", "test123", "arne.lauryssens@student.kdg.be", null, null);
        presenter.updatePassword(TOKEN);

        verify(service).updateCurrentUserPassword(anyString(), any(User.class), stringCallbackCaptop.capture());

        stringCallbackCaptop.getValue().success(anyString(), null);
        verify(view).showSucces();

    }
}
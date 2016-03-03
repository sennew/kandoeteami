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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class UserPresenterTest {
    private static final String TOKEN = "\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBcm5lTGF1cnlzc2VucyJ9.dblX_wcZ-FMOTqwhnVBvUVIthiR3YvRSLPt_mFds-PU\"";

    @Mock
    private UserService service;
    @Mock
    private UserContract.View view;

    private UserPresenter presenter;

    @Captor
    private ArgumentCaptor<Callback<User>> argumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new UserPresenter(view, service);
    }

    @Test
    public void testShouldLoadUser(){
        presenter.loadUser(TOKEN);

        verify(service).getCurrentUser(anyString(), argumentCaptor.capture());

        User t = new User();
        argumentCaptor.getValue().success(t, null);
        verify(view).showUserDetails(t);
    }

    @Test
    public void testShouldUpdateUser() throws Exception {
        User user = new User(1, "ArneLauryssens", "test123", "test123", "arne.lauryssens@student.kdg.be", null, null);
        presenter.updateUser(TOKEN, user);

        verify(service).updateCurrentUser(anyString(), any(User.class), argumentCaptor.capture());

        User t = new User();
        argumentCaptor.getValue().success(t, null);
        verify(view).showUserDetails(t);
    }
}
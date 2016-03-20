package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.login.LoginContract;
import com.projects.wens.kandoeteami.login.LoginPresenter;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;
import com.projects.wens.kandoeteami.retrofit.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class LoginPresenterTest {
    @Mock
    private LoginService mloginService;
    @Mock
    private LoginContract.view mLoginView;

    private LoginPresenter mLoginPresenter;

    @Mock
    private UserService mUserService;

    @Captor
    private ArgumentCaptor<String> mLoginTokenCallbackCaptop;

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mloginService = ServiceGenerator.createService(LoginService.class,"http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mUserService = ServiceGenerator.createService(UserService.class,"http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mLoginPresenter = new LoginPresenter(mLoginView, mloginService, mUserService);
    }

    @Test
    public void checkPasswordNotEmpty() {
        when(mLoginView.getUsername()).thenReturn("ArneLauryssens");
        when(mLoginView.getPassword()).thenReturn("");
        mLoginPresenter.login();
        verify(mLoginView).showPasswordError("Password is empty!");
    }

    @Test
    public void checkUsernameNotEmpty(){
        when(mLoginView.getUsername()).thenReturn("");
        when(mLoginView.getPassword()).thenReturn("test123");
        mLoginPresenter.login();
        verify(mLoginView).showUsernameError("Username is empty");
    }
}
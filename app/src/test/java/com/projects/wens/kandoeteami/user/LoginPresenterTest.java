package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.login.LoginContract;
import com.projects.wens.kandoeteami.login.LoginPresenter;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by senne on 29/02/2016.
 */
public class LoginPresenterTest {
    @Mock
    private LoginService mloginService;
    @Mock
    private LoginContract.view mLoginView;

    private LoginPresenter mLoginPresenter;

    @Captor
    private ArgumentCaptor<String> mLoginTokenCallbackCaptop;

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mloginService = ServiceGenerator.createService(LoginService.class,"http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mLoginPresenter = new LoginPresenter(mLoginView, mloginService);
    }

    @Test
    public void checkLogin(){
        verify(mLoginView).showProgressLogin();
        verify(mLoginView).showSuccessMessage("Login correct");
    }

    @Test
    public void checkLoginFailure(){
        when(mLoginView.getUsername()).thenReturn("ArneLauryssens");
        when(mLoginView.getPassword()).thenReturn("");
        mLoginPresenter.login();
        verify(mLoginView).showProgressLogin();
        verify(mLoginView).showErrorMessage("Login failed.");
    }

    @Test
    public void checkPasswordNotEmpty() {
        when(mLoginView.getUsername()).thenReturn("ArneLauryssens");
        when(mLoginView.getPassword()).thenReturn("");
        mLoginPresenter.login();
        verify(mLoginView).showProgressLogin();
        verify(mLoginView).showPasswordError("Password is empty!");
    }

    @Test
    public void checkUsernameNotEmpty(){
        when(mLoginView.getUsername()).thenReturn("");
        when(mLoginView.getPassword()).thenReturn("test123");
        mLoginPresenter.login();
        verify(mLoginView).showProgressLogin();
        verify(mLoginView).showUsernameError("Username is empty");
    }
}
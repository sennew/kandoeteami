package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.register.RegisterContract;
import com.projects.wens.kandoeteami.register.RegisterPresenter;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.RegisterService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class RegisterPresenterTest {
    @Mock
    RegisterService mRegisterService;
    @Mock
    private RegisterContract.view mRegisterView;

    private RegisterPresenter mRegisterPresenter;

    @Before
    public void setmRegisterPresenter(){
        MockitoAnnotations.initMocks(this);
        mRegisterService = ServiceGenerator.createService(RegisterService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mRegisterPresenter = new RegisterPresenter(mRegisterView, mRegisterService);
    }

    @Test
    public void checkUsernameNotNull(){
        when(mRegisterView.getUsername()).thenReturn("");
        when(mRegisterView.getEmail()).thenReturn("sennewens@hotmail.com");
        when(mRegisterView.getPassword()).thenReturn("hallo");
        when(mRegisterView.getRetypePassword()).thenReturn("hallo");
        mRegisterPresenter.register(null);
        verify(mRegisterView).showUsernameError("Username is empty");
    }

    @Test
    public void checkEmailNotNull(){
        when(mRegisterView.getUsername()).thenReturn("sennew");
        when(mRegisterView.getEmail()).thenReturn("");
        when(mRegisterView.getPassword()).thenReturn("hallo");
        when(mRegisterView.getRetypePassword()).thenReturn("hallo");
        mRegisterPresenter.register(null);
        verify(mRegisterView).showEmailError("Email is empty");
    }

    @Test
    public void checkPasswordNotNull(){
        when(mRegisterView.getUsername()).thenReturn("sennew");
        when(mRegisterView.getEmail()).thenReturn("sennewens@hotmail.com");
        when(mRegisterView.getPassword()).thenReturn("");
        when(mRegisterView.getRetypePassword()).thenReturn("hallo");
        mRegisterPresenter.register(null);
        verify(mRegisterView).showPasswordError("Password is empty!");
    }

    @Test
      public void checkRetypePasswordNotNull(){
        when(mRegisterView.getUsername()).thenReturn("sennew");
        when(mRegisterView.getEmail()).thenReturn("sennewens@hotmail.com");
        when(mRegisterView.getPassword()).thenReturn("hallo");
        when(mRegisterView.getRetypePassword()).thenReturn("");
        mRegisterPresenter.register(null);
        verify(mRegisterView).showRetypePasswordError("Passwords do not match!");
    }

    @Test
    public void check2WrongPasswordsNotNull(){
        when(mRegisterView.getUsername()).thenReturn("sennew");
        when(mRegisterView.getEmail()).thenReturn("sennewens@hotmail.com");
        when(mRegisterView.getPassword()).thenReturn("hallo2");
        when(mRegisterView.getRetypePassword()).thenReturn("hallo");
        mRegisterPresenter.register(null);
        verify(mRegisterView).showRetypePasswordError("Passwords do not match!");
    }
}

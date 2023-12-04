package example.com.cmsandroidsimulation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import example.com.cmsandroidsimulation.mvplogin.LoginModel;
import example.com.cmsandroidsimulation.mvplogin.LoginPresenter;
import example.com.cmsandroidsimulation.mvplogin.LoginView;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    LoginView view;
    @Mock
    LoginModel model;

    @Test
    public void viewCheckEmptyUsername(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.validateCredentials("", "");
        verify(view).showUsernameError();
    }
    @Test
    public void viewCheckEmptyPassword(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.validateCredentials("conradmo@gmail.com", "");
        verify(view).showPasswordError();
    }
    @Test
    public void viewCheckNullUsername(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.validateCredentials(null, null);
        verify(view).showUsernameError();
    }
    @Test
    public void viewCheckNullPassword(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.validateCredentials("conradmo@gmail.com", null);
        verify(view).showPasswordError();
    }
    @Test
    public void viewCheckLoginFail(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.onLoginFailed("Login failed");
        verify(view).showLoginFailed("Login failed");
    }
    @Test
    public void viewCheckLoginSuccess(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.onLoginSuccess(null);
        verify(view).showLoginSuccess(null);
    }
    @Test
    public void modelCheckLogin(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.validateCredentials("conradmo@gmail.com", "123456");
        verify(model).login(presenter, "conradmo@gmail.com", "123456");
    }
//
//    @Test
//    public void checkDBClick(){
//        when(editText.getText()).thenReturn(edit);
//        when(edit.toString()).thenReturn("Test");
//    }
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
}
package example.com.cmsandroidsimulation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import example.com.cmsandroidsimulation.mvplogin.LoginModel;
import example.com.cmsandroidsimulation.mvplogin.LoginPresenter;
import example.com.cmsandroidsimulation.mvplogin.LoginView;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginModel model;

    @Mock
    LoginView view;

    @Test
    public void checkEmptyUsername(){
        LoginPresenter presenter = new LoginPresenter(view, model);
        presenter.validateCredentials("", "");
        verify(view).showUsernameError();
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
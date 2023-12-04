package example.com.cmsandroidsimulation.mvplogin;

import example.com.cmsandroidsimulation.apiwrapper.User;

public class LoginPresenter {
    private final LoginView loginView;
    private final LoginModel loginModel;

    public LoginPresenter(LoginView loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }

    public void validateCredentials(String email, String password) {
        if (email == null || email.equals("")) {
            loginView.showUsernameError();
            return;
        }

        if (password == null || password.equals("")) {
            loginView.showPasswordError();
            return;
        }

        loginModel.login(this, email, password);
    }
    public void onLoginFailed(String loginFailed)
    {
        loginView.showLoginFailed(loginFailed);
    }
    public void onLoginSuccess(User user)
    {
        loginView.showLoginSuccess(user);
    }
}
package example.com.cmsandroidsimulation.mvplogin;

import example.com.cmsandroidsimulation.apiwrapper.User;

public class LoginPresenter {
    public LoginView loginView;
    public LoginModel loginModel;

    public LoginPresenter(LoginView loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }

    public void validateCredentials(String email, String password) {
        loginModel.login(email, password, new LoginModel.OnLoginFinishedListener() {

            public void usernameEmpty(){
                if (email.equals("")){
                    loginView.showUsernameError();
                }
            }
            @Override
            public void onUsernameError() {
                if (loginView != null) {
                    loginView.showUsernameError();
                }
            }

            @Override
            public void onPasswordError() {
                if (loginView != null) {
                    loginView.showPasswordError();
                }
            }

            @Override
            public void onLoginSuccess(User user) {
                if (loginView != null) {
                    loginView.showLoginSuccess(user);
                }
            }

            @Override
            public void onLoginFailed(String errorMessage) {
                if (loginView != null) {
                    loginView.showLoginFailed(errorMessage);
                }
            }
        });
    }
}
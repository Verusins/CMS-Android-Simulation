package example.com.cmsandroidsimulation.mvplogin;

import example.com.cmsandroidsimulation.apiwrapper.User;

public interface LoginView {
    void showUsernameError();
    void showPasswordError();
    void showLoginSuccess(User user);
    void showLoginFailed(String errorMessage);
}
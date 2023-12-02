package example.com.cmsandroidsimulation.views;

import example.com.cmsandroidsimulation.presenters.User;

public interface LoginView {
    void showUsernameError();
    void showPasswordError();
    void showLoginSuccess(User user);
    void showLoginFailed(String errorMessage);
}
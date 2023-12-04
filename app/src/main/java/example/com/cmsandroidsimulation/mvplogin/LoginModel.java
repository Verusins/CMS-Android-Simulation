package example.com.cmsandroidsimulation.mvplogin;

import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.apiwrapper.User;

public class LoginModel {
    public void login(LoginPresenter presenter, String email, String password) {

        CompletableFuture<User> cf = User.Login(email, password);
        cf.thenAccept(user -> {
            if (user != null) {
                presenter.onLoginSuccess(user);
            } else {
                presenter.onLoginFailed("Login failed");
            }
        }).exceptionally(e -> {
            presenter.onLoginFailed(e.getMessage());
            return null;
        });
    }
}
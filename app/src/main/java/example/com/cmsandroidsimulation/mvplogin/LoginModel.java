package example.com.cmsandroidsimulation.mvplogin;

import android.text.TextUtils;

import java.io.Console;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.apiwrapper.User;

public class LoginModel {
    public interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onLoginSuccess(User user);
        void onLoginFailed(String errorMessage);
    }

    public void login(String email, String password, OnLoginFinishedListener listener) {
        if (TextUtils.isEmpty(email)) {
            listener.onUsernameError();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }

        CompletableFuture<User> cf = User.Login(email, password);
        cf.thenAccept(user -> {
            if (user != null) {
                listener.onLoginSuccess(user);
            } else {
                listener.onLoginFailed("Login failed");
            }
        }).exceptionally(e -> {
            listener.onLoginFailed(e.getMessage());
            return null;
        });
    }
}
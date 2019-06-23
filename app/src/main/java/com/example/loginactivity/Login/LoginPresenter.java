package com.example.loginactivity.Login;

import com.example.loginactivity.Login.LoginInterface;
import com.example.loginactivity.LoginActivity;
import com.example.loginactivity.LoginModel;

public class LoginPresenter implements LoginInterface.Presented, LoginInterface, LoginInterface.TaskListener {

    private LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(View view) {
        this.view = view;
        model = new LoginModel(this);
    }

    @Override
    public void onDestroy() {
        view = null;

    }

    @Override
    public void toLogin(String email, String password) {
        if (view != null){
            view.disableInputs();
            view.showProgress();

        }
        model.doLogin(email,password);
    }

    @Override
    public void onSucces() {
        if (view != null){
            view.enableInputs();
            view.hideProgress();
            view.onLogin();
        }

    }

    @Override
    public void onError(String error) {
        if (view != null){
            view.enableInputs();
            view.hideProgress();
            view.onError(error);
        }
    }
}

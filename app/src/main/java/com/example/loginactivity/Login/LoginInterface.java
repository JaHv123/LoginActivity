package com.example.loginactivity.Login;

public interface LoginInterface {
    interface View{

        void disableInputs();
        void enableInputs();

        void showProgress();
        void hideProgress();

        void handleLogin();

        boolean isvalidEmail();
        boolean isValidPassword();

        void onLogin();
        void onError(String error);
    }

    interface Presented{
        void onDestroy();

        void toLogin(String email, String password);
    }

    interface Model {
        void doLogin(String email, String Password);
    }

    interface TaskListener{
        void onSucces();
        void onError(String error);
    }
}

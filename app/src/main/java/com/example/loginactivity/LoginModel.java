package com.example.loginactivity;

import android.support.annotation.NonNull;

import com.example.loginactivity.Login.LoginInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModel implements LoginInterface.Model {

    private LoginInterface.TaskListener listener;
    private FirebaseAuth auth;

    public LoginModel(LoginInterface.TaskListener listener) {
        this.listener = listener;
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void doLogin(String email, String Password) {
        auth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    listener.onSucces();
                }else{
                    if (task.getException() != null){
                        listener.onError(task.getException().getMessage());
                    }
                }
            }
        });
    }
}

package com.example.loginactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.loginactivity.Login.LoginInterface;
import com.example.loginactivity.Login.LoginPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {

    private MaterialEditText edEmail, edPassword;
    private FancyButton btnSigning;
    private LoginInterface.Presented presented;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        btnSigning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });
    }

    private void setViews() {
        presented = new LoginPresenter(this);
        edEmail = findViewById(R.id.edtEmail);
        edPassword = findViewById(R.id.edtPassword);
        btnSigning = findViewById(R.id.btnSinging);



    }

    private void setInputs(boolean enable){
        edEmail.setEnabled(enable);
        edPassword.setEnabled(enable);
        btnSigning.setEnabled(enable);
    }

    @Override
    public void disableInputs() {
        setInputs(false);

    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void showProgress() {


    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void handleLogin() {
        if (!isvalidEmail()){
            Toast.makeText(this,"No es un email válido",Toast.LENGTH_SHORT).show();
        }else if (!isValidPassword()){
            Toast.makeText(this,"No es un password válido",Toast.LENGTH_SHORT).show();
        }else{
            presented.toLogin(edEmail.getText().toString().trim(), edPassword.getText().toString());
        }

    }

    @Override
    public boolean isvalidEmail() {
        return Patterns.EMAIL_ADDRESS.matcher(edEmail.getText().toString()).matches();
    }

    @Override
    public boolean isValidPassword() {
        if (TextUtils.isEmpty(edPassword.getText().toString()) && edPassword.getText().toString().length()<4){
            Toast.makeText(this, "No es una contraseña válida", Toast.LENGTH_SHORT).show();
            edPassword.setError("No es una contraseña válida");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onLogin() {
        Toast.makeText(this, "has hecho Login Correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

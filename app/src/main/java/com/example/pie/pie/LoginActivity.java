package com.example.pie.pie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText emailLoginEditText;
    private EditText passwordLoginEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setComponents();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateLogin();
            }
        });
    }

    private void initiateLogin() {

    }

    private void setComponents() {
        emailLoginEditText = findViewById(R.id.emailTextView);
        passwordLoginEditText = findViewById(R.id.passwordTextView);
        loginButton = findViewById(R.id.loginBtn);
    }


}

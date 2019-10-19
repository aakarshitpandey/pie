package com.example.pie.pie;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailSignUpEditText;
    private EditText passwordSignUpEditText;
    private Button signUpBtn;
    private String emailSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateSignUp();
            }
        });
    }

    private void initiateSignUp() {
    }

    private void getSignUpComponents() {
        emailSignUpEditText = findViewById(R.id.emailSignUpText);
        passwordSignUpEditText = findViewById(R.id.passwordSignUpText);
        signUpBtn = findViewById(R.id.signUpBtn);

        emailSignUpEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailSignUpEditText.setText("");
            }
        });
        if (!emailSignUpEditText.hasFocus()) {
            emailSignUp = emailSignUpEditText.getText().toString();
        }
    }

}

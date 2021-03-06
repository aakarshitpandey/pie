package com.example.pie.pie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button signIn;
        Button signUp;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.logInBtn);
        signUp = findViewById(R.id.signUpBtn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI("signIn");
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI("signUp");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void updateUI(String mode) {
        if (mode.equals("signIn")) {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        } else if (mode.equals("signUp")) {
            Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
            startActivity(intent);
        }
    }
}

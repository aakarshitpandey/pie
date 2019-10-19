package com.example.pie.pie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText emailLoginEditText;
    private EditText passwordLoginEditText;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private String emailLoginText;
    private String passwordLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setComponents();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initiateLogin(emailLoginEditText.getText().toString().trim(),
//                        passwordLoginEditText.getText().toString().trim());
                initiateLogin(emailLoginText, passwordLoginText);
            }
        });

        //auth
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser); //user already signed in
    }

    private void getLoginComponents() {
        emailLoginEditText = findViewById(R.id.emailTextView);
        passwordLoginEditText = findViewById(R.id.passwordTextView);
        loginButton = findViewById(R.id.loginBtn);

        emailLoginEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailLoginEditText.setText("");
            }
        });
        if (!emailLoginEditText.hasFocus()) {
            emailLoginText = emailLoginEditText.getText().toString().trim();
        }

        passwordLoginEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordLoginEditText.setText("");
            }
        });
        if (!passwordLoginEditText.hasFocus()) {
            passwordLoginText = passwordLoginEditText.getText().toString().trim();
        }

    }

    private void initiateLogin(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCCESS", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user); //redirect to the dashboard
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w("createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null); //authentication failed
                        }
                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void setComponents() {
        emailLoginEditText = findViewById(R.id.emailTextView);
        passwordLoginEditText = findViewById(R.id.passwordTextView);
        loginButton = findViewById(R.id.loginBtn);
    }


}

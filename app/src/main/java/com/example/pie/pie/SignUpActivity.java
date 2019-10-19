package com.example.pie.pie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
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

public class SignUpActivity extends AppCompatActivity {
    private EditText emailSignUpEditText;
    private EditText passwordSignUpEditText;
    private Button signUpBtn;
    private String emailSignUp;
    private String passwordSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //auth
        mAuth = FirebaseAuth.getInstance();

        setComponents();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSignUpComponents();
                initiateSignUp();
            }
        });
    }

    private void initiateSignUp() {
        mAuth.createUserWithEmailAndPassword(emailSignUp, passwordSignUp)
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
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
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

    private void getSignUpComponents() {
        emailSignUp = emailSignUpEditText.getText().toString().trim();
        passwordSignUp = passwordSignUpEditText.getText().toString().trim();
    }

    private void setComponents() {
        emailSignUpEditText = findViewById(R.id.emailSignUpText);
        passwordSignUpEditText = findViewById(R.id.passwordSignUpText);
        signUpBtn = findViewById(R.id.signUpBtn);
    }

}

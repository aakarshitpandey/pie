package com.example.pie.pie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    Button logoutBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView textView = findViewById(R.id.textView2); //testing
        String welcomeMessage = "Welcome " + getIntent().getStringExtra("username") + "!";
        textView.setText(welcomeMessage);
        mAuth = FirebaseAuth.getInstance();
        logoutBtn = findViewById(R.id.logoutButton);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                logout();
            }
        });
    }

    public void logout() {
        mAuth.signOut();
        //TODO
    }
}

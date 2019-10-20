package com.example.pie.pie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView textView = findViewById(R.id.textView2); //testing
        String welcomeMessage = "Welcome " + getIntent().getStringExtra("username") + "!";
        textView.setText(welcomeMessage);
    }
}

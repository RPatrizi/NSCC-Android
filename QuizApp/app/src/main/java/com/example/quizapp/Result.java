package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    // create views
    TextView tvFinalResult, tvGreeting;
    Button btRestart, btQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // create variables
        int finalScore = getIntent().getIntExtra("FinalScore", 0);
        String greeting = "";

        // Set up views
        tvFinalResult = findViewById(R.id.tvFinalResult);
        tvGreeting = findViewById(R.id.tvGreeting);
        btRestart = findViewById(R.id.btRestart);
        btQuit = findViewById(R.id.btQuit);

        // Set all events to same listener class
        btRestart.setOnClickListener(onButtonClick);
        btQuit.setOnClickListener(onButtonClick);

        // display final result
        tvFinalResult.setText(String.valueOf(finalScore));

        // IF statement to define greeting and display
        if (finalScore >=7) {
            greeting = "Congratulations!!";
        } else {
            greeting = "Study more :(";
        }
        tvGreeting.setText(greeting);
    } // end onCreate

    // setup onClickListener to restart or quit app
    public View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btRestart:
                    restart();
                    break;

                case R.id.btQuit:
                    quitApp();
                    break;
            }
        }
    }; // end onClickListener

    // intent to exit app
    public void quitApp () {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
    } // end quitApp

    // intent to restart app
    public void restart () {
        Intent intent = new Intent(Result.this, MainActivity.class);
        startActivity(intent);
    } // end quitApp
} // end Result class
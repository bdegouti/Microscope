package com.example.microscope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setPlayButtonListener();
        setupNewGameButtonListener();
        setupLoadGameListener();
        setGameStartVisibility();
    }

    private void setGameStartVisibility() {
        ImageView ClickDetector = findViewById(R.id.MainMenu_IV_ClickDetector);
        ClickDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView gameStart = findViewById(R.id.MainMenu_CV_gameStart);
                gameStart.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupLoadGameListener() {
        Button loadGameBTN = findViewById(R.id.MainMenu_BTN_loadSavedGame);
        loadGameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: set up load game menu
            }
        });
    }

    private void setupNewGameButtonListener() {
        Button newGameBTN = findViewById(R.id.MainMenu_BTN_newGame);
        newGameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = NewGameSetup.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setPlayButtonListener() {
        Button playBtn = findViewById(R.id.MainMenu_playButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView gameStart = findViewById(R.id.MainMenu_CV_gameStart);
                gameStart.setVisibility(View.VISIBLE);
            }
        });
    }
}
package com.example.microscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.microscope.model.GameManager;

public class StartActivity extends AppCompatActivity {
    private GameManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        gm = GameManager.getInstance();
        setStartButtonListener();
    }

    private void setStartButtonListener() {
        Button btnStart = findViewById(R.id.start_BTN_startButton);
        RadioGroup rgNumOfPlayers = findViewById(R.id.start_RG_numOfPlayers);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedNum = rgNumOfPlayers.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedNum);
                if(selectedNum == R.id.start_RB_1player) {
                    gm.addPlayers(1);
                }
                else if(selectedNum == R.id.start_RB_2players){
                    gm.addPlayers(2);
                }
                else if(selectedNum == R.id.start_RB_3players){
                    gm.addPlayers(3);
                }
                else if(selectedNum == R.id.start_RB_4players){
                    gm.addPlayers(4);
                }
                else if(selectedNum == R.id.start_RB_5players){
                    gm.addPlayers(5);
                }
                else if(selectedNum == R.id.start_RB_6players){
                    gm.addPlayers(6);
                }

                Intent intent = PaletteActivity.makeIntent(StartActivity.this);
                startActivity(intent);
            }
        });
    }
}
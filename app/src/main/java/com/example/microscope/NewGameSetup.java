package com.example.microscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.microscope.model.GameManager;

public class NewGameSetup extends AppCompatActivity {
    private GameManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_setup);

        gm = GameManager.getInstance();
        setStartButtonListener();
    }

    public static Intent makeIntent(Context c){ return new Intent(c, NewGameSetup.class);}

    private void finishActivity(){
        this.finish();
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

                EditText gameName = findViewById(R.id.start_ET_gameName);
                gm.setGameName(gameName.getText().toString());

                Intent intent = PaletteActivity.makeIntent(NewGameSetup.this);
                startActivity(intent);
            }
        });
    }
}
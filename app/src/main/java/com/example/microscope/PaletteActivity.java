package com.example.microscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microscope.model.GameManager;
import com.example.microscope.model.GameObjects.Player;
import com.example.microscope.model.Palette;

public class PaletteActivity extends AppCompatActivity {

    private GameManager gm;
    private Palette palette;
    private Player startingPlayer;
    private boolean playerHasPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        gm = GameManager.getInstance();
        startingPlayer = gm.getCurrTurn();
        palette = new Palette();
        playerHasPassed = false;

        TextView playerTurn = findViewById(R.id.palette_TV_currPlayer);
        playerTurn.setText(gm.getCurrTurn().getName());

        setupAddButton();
        setupPassButton();

    }

    @Override
    protected void onPause() {
        gm.resetTurnsAndRoundsTo0();
        super.onPause();
    }

    private boolean checkIfTextEmpty(){
        TextView entryTV = findViewById(R.id.palette_UTXT_enterRule);
        return entryTV.getText().toString().equals("");
    }

    private void setupPassButton() {
        Button passBTN = findViewById(R.id.palette_BTN_pass);
        passBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerHasPassed = true;
                turnOver();
            }
        });
    }

    private void setupAddButton() {
        Button addBTN = findViewById(R.id.palette_BTN_addRule);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfTextEmpty()) {
                    TextView entryTV = findViewById(R.id.palette_UTXT_enterRule);
                    palette.addRule(entryTV.getText().toString());
                    turnOver();
                }
                else{
                    Toast.makeText(PaletteActivity.this, "Brevity is the soul of wit, but you can't add what doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void turnOver(){
        gm.nextTurn();
        Player tmp = gm.getCurrTurn();

        if(playerHasPassed && tmp == startingPlayer){
            gm.setPalette(palette);

            Intent intent = SetupStartPeriodActivity.makeIntent(PaletteActivity.this);
            startActivity(intent);
        }

        TextView playerTurn = findViewById(R.id.palette_TV_currPlayer);
        playerTurn.setText(tmp.getName());

        TextView entryTV = findViewById(R.id.palette_UTXT_enterRule);
        entryTV.setText("");

    }

    public static Intent makeIntent(Context c){ return new Intent(c, PaletteActivity.class);}


}
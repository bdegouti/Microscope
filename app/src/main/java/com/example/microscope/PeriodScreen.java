package com.example.microscope;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.microscope.model.GameManager;
import com.example.microscope.model.GameObjects.Card;
import com.example.microscope.model.GameObjects.Period;
import com.example.microscope.model.Tone;

public class PeriodScreen extends AppCompatActivity {
    private GameManager gm;
    private boolean addModeOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_screen);

        gm = GameManager.getInstance();
        addModeOn = false;
        populatePeriodsList();
        setupAddPeriodButton();
        setupEndTurnButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populatePeriodsList();
        addModeOn = false;
    }

    public static Intent makeIntent(Context c){ return new Intent(c, PeriodScreen.class);}

    private void populatePeriodsList() {
        int layout;
        if(addModeOn){
            layout = R.layout.card_add_view;
        }
        else{
            layout = R.layout.card_view;
        }

        ArrayAdapter<Period> periodListAdapter = new CardListAdapter(layout, addModeOn);
        ListView listView = findViewById(R.id.period_LV_periodsList);
        listView.setAdapter(periodListAdapter);
        listView.setDivider(null);
    }

    public class CardListAdapter extends ArrayAdapter<Period>{
        boolean addModeOn;
        public CardListAdapter(int layout, boolean addModeOn){
            super(PeriodScreen.this, layout, gm.getPeriods());
            this.addModeOn = addModeOn;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View cardView = convertView;
            if(cardView == null){
                if(addModeOn){
                    cardView = getLayoutInflater().inflate(R.layout.card_add_view, parent, false);
                }
                else {
                    cardView = getLayoutInflater().inflate(R.layout.card_view, parent, false);
                }
            }

            Card currCard = gm.getPeriod(position);
            TextView titleTV = cardView.findViewById(R.id.cardView_TV_title);
            TextView descriptionTV = cardView.findViewById(R.id.cardView_TV_description);
            ImageView toneIV = cardView.findViewById(R.id.cardView_IV_Tone);
            TextView containerSizeTV = cardView.findViewById(R.id.cardView_TV_containerSize);

            if(addModeOn){
                ImageButton addButton = cardView.findViewById(R.id.CVA_BTN_addButton);
                if(position == gm.getNumberOfPeriods()-1){
                    addButton.setVisibility(View.INVISIBLE);
                }
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = AddPeriodActivity.makeIntent(PeriodScreen.this, position);
                        startActivity(intent);
                    }
                });
            }

            titleTV.setText(currCard.getName());
            descriptionTV.setText(currCard.getDescription());

            if(currCard.getTone() == Tone.Dark){
                toneIV.setImageResource(R.drawable.circle_filled);
            }
            else if(currCard.getTone() == Tone.Light){
                toneIV.setImageResource(R.drawable.circle_not_filled);
            }

            containerSizeTV.setText(String.valueOf(currCard.getSubListSize()));

            return cardView;
        }

        @Override
        public boolean isEnabled(int position) {
            if(addModeOn){
                if(position == gm.getNumberOfPeriods()-1){
                    return false;
                }
            }
            return super.isEnabled(position);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PeriodScreen.this);
        builder.setTitle("Are you sure you want to exit this game without saving?");

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(PeriodScreen.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setupAddPeriodButton() {
        Button addPeriodBTN = findViewById(R.id.period_BTN_newPeriod);
        addPeriodBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addModeOn){
                    addModeOn = false;
                }
                else{
                    addModeOn = true;
                }
                populatePeriodsList();
            }
        });
    }

    private void setupEndTurnButton() {
        Button endTurnButton = findViewById(R.id.period_BTN_endTurn);
        endTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddPeriodActivity.makeIntent(PeriodScreen.this, 0);
                startActivity(intent);
            }
        });
    }
}
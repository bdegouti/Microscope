package com.example.microscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microscope.model.GameManager;
import com.example.microscope.model.GameObjects.Period;
import com.example.microscope.model.Tone;

public class AddPeriodActivity extends AppCompatActivity {
    private static final String TASK_INDEX = "task index";
    private GameManager gm;
    private Period period;
    private int indexOfPeriodPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_period);
        gm = GameManager.getInstance();

        period = new Period();

        Intent intentLeadingToMe = getIntent();
        indexOfPeriodPrevious = intentLeadingToMe.getIntExtra(TASK_INDEX, -1);

        setupToneButton();
        setupSubmitButton();
    }

    public static Intent makeIntent(Context c, int index){
        Intent intent = new Intent(c, AddPeriodActivity.class);
        intent.putExtra(TASK_INDEX, index);

        return intent;
    }

    private boolean checkIfTitleEmpty(){
        TextView entryTV = findViewById(R.id.card_PT_periodTitle);
        return entryTV.getText().toString().equals("");
    }

    //Card Input functions
    private void setupToneButton() {
        ImageView toneIV = findViewById(R.id.card_IV_tone);
        toneIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(period.getTone() == Tone.Light){
                    period.setTone(Tone.Dark);
                    toneIV.setImageResource(R.drawable.circle_filled);
                }
                else if(period.getTone() == Tone.Dark){
                    period.setTone(Tone.Light);
                    toneIV.setImageResource(R.drawable.circle_not_filled);
                }


            }
        });
    }

    private void setupSubmitButton() {
        Button submitBTN = findViewById(R.id.addPeriod_BTN_addPeriod);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkIfTitleEmpty()){
                    TextView titleTV = findViewById(R.id.card_PT_periodTitle);
                    TextView contentTV = findViewById(R.id.card_PT_cardDescription);

                    period.setName(titleTV.getText().toString());
                    period.setDescription(contentTV.getText().toString());

                    gm.addPeriodAt(period, indexOfPeriodPrevious+1);

                    finish();
                }
                else{
                    Toast.makeText(AddPeriodActivity.this, "A Period must have a title (though a description is optional)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
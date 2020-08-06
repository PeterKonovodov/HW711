package com.example.hw711;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    static final int MORNING = 0;
    static final int AFTERNOON = 1;
    static final int EVENING = 2;
    private int dayTime = MORNING;

    private Button morning_btn;
    private Button afternoon_btn;
    private Button evening_btn;
    private Button sync_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey("dayTime"))  dayTime = savedInstanceState.getInt("dayTime");
        }


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.morning_btn:
                        dayTime = MORNING;
                        break;
                    case R.id.afternoon_btn:
                        dayTime = AFTERNOON;
                        break;
                    case R.id.evening_btn:
                        dayTime = EVENING;
                        break;
                    case R.id.sync_btn:

                        Intent intent = new Intent(Intent.ACTION_SYNC);

                        switch (dayTime) {
                            case MORNING:
                                intent.setData(Uri.parse("http://morning"));
                                break;
                            case AFTERNOON:
                                intent.setData(Uri.parse("http://afternoon"));
                                break;
                            case EVENING:
                                intent.setData(Uri.parse("http://evening"));
                                break;
                        }
                        startActivity(intent);
                        break;
                    default:
                        return;

                }
                setButtonColor();

            }
        };

        morning_btn = findViewById(R.id.morning_btn);
        afternoon_btn = findViewById(R.id.afternoon_btn);
        evening_btn = findViewById(R.id.evening_btn);
        sync_btn = findViewById(R.id.sync_btn);
        morning_btn.setOnClickListener(onClickListener);
        afternoon_btn.setOnClickListener(onClickListener);
        evening_btn.setOnClickListener(onClickListener);
        sync_btn.setOnClickListener(onClickListener);
        setButtonColor();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("dayTime", dayTime);
    }

    private void setButtonColor() {
        switch(dayTime) {
            case MORNING:
                morning_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSelectedButton)));
                afternoon_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButton)));
                evening_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButton)));
                break;
            case AFTERNOON:
                morning_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButton)));
                afternoon_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSelectedButton)));
                evening_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButton)));
                break;
            case EVENING:
                morning_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButton)));
                afternoon_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButton)));
                evening_btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSelectedButton)));
                break;
            default:
                break;
        }

    }


}
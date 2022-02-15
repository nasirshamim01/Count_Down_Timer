package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IM_MILLIS = 600000;
    //STARTING TIIME FOR TIMER

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    //member variables

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    //tells whether timer is running or not

    private long mTimeLeftInMillis = START_TIME_IM_MILLIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        //take mTextViewCountDown variable

        mButtonStartPause = findViewById(R.id.button_start_pause);

        mButtonReset = findViewById(R.id.button_reset);


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }

            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
           //var for milisec
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                //set it to millisuntilfin... variable
                updateCountDownText();
                        //we have to update so we keep it in a seprate method

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");//timer is not running
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();//create timer and immediately start it

        mTimerRunning = true;
        //coz now our timer is running
        mButtonStartPause.setText("paused");
        //timer is running so we set our button to paused
        mButtonReset.setVisibility(View.INVISIBLE);
        //set reset button to invisible, coz when we pass our timer we willl make this button invisible


    }

    private void pauseTimer(){
        mCountDownTimer.cancel();//cancel timer here
        mTimerRunning = false;//stop running timer
        mButtonStartPause.setText("Start");//after clicking pause chane text to start
        mButtonReset.setVisibility(View.VISIBLE);//making pause/start buttom visible

    }

    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IM_MILLIS; //start to its default time
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);//bcz after reset we want to invisible it
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        //here we convert millisec into min and sec
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        //turns millisec in   minutes
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        //turns millisec into sec
        //now we will format this values in string so actually loooks like clock

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
        //we pass out formatted string here

    }
}
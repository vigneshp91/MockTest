package com.mocktest;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity {
    TextView _tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

               _tv = (TextView) findViewById( R.id.timer );
        new CountDownTimer(20*60000, 1000) {

            public void onTick(long millisUntilFinished) {
//                _tv.setText(new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date( millisUntilFinished)));
                _tv.setText("Time Remaining "+String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                _tv.setText("done!");
            }
        }.start();
    }
}

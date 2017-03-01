package com.mocktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

/*        TextView _tv = (TextView) findViewById( R.id.textView1 );
        new CountDownTimer(20*60000, 1000) {

            public void onTick(long millisUntilFinished) {
                _tv.setText("seconds remaining: " +new SimpleDateFormat("mm:ss:SS").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                _tv.setText("done!");
            }
        }.start();*/
    }
}

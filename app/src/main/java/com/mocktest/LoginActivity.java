package com.mocktest;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.mocktest.database.MockTestDb;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText uname,password;
    private RadioButton student,trainer;
    private TextInputLayout usernameWrapper,passwordWrapper;
    private MockTestDb mTestdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button submit=(Button)findViewById(R.id.submit);
        uname=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        passwordWrapper=(TextInputLayout)findViewById(R.id.passwordWrapper);
        usernameWrapper=(TextInputLayout)findViewById(R.id.usernameWrapper);
        student=(RadioButton)findViewById(R.id.student_login);
        trainer=(RadioButton)findViewById(R.id.trainer_login);
        submit.setOnClickListener(this);

        mTestdb=new MockTestDb(getApplicationContext());

        uname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameWrapper.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordWrapper.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                String uname_txt=uname.getText().toString();
                String pass_txt=password.getText().toString();
                String user_type="";

                if(student.isChecked())
                    user_type="S";
                if(trainer.isChecked())
                    user_type="T";


                if(user_type.equalsIgnoreCase(""))
                    Toast.makeText(this, "Select Type of user", Toast.LENGTH_SHORT).show();
                else if(uname.getText().toString().equalsIgnoreCase("")){
                    usernameWrapper.setErrorEnabled(true);
                    usernameWrapper.setError("Enter Username");
                }else if(pass_txt.equalsIgnoreCase("")){
                    passwordWrapper.setErrorEnabled(true);
                    passwordWrapper.setError("Enter Password");
                }else if(mTestdb.checkUser(uname_txt).getCount()>0){
                    usernameWrapper.setErrorEnabled(true);
                    usernameWrapper.setError("User Already Exist");
            }
                else if(!uname_txt.equalsIgnoreCase("")){
                 TestUtil.getInstance().saveUser(getApplicationContext(),uname_txt);
                    mTestdb.registerUser(uname_txt,pass_txt,user_type);
                }
                break;
        }
    }
}

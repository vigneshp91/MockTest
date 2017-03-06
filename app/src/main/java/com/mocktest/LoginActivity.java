package com.mocktest;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mocktest.database.MockTestDb;
import com.mocktest.pojo.QuestionList;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText uname,password;
    private RadioButton student,trainer;
    private TextInputLayout usernameWrapper,passwordWrapper;
    private MockTestDb mTestdb;
    private TextView user_hint,register_link;
    Button submit;
    private static int action_type=0;
    String uname_txt,user_type,pass_txt,db_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submit=(Button)findViewById(R.id.submit);
        uname=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        passwordWrapper=(TextInputLayout)findViewById(R.id.passwordWrapper);
        usernameWrapper=(TextInputLayout)findViewById(R.id.usernameWrapper);
        student=(RadioButton)findViewById(R.id.student_login);
        trainer=(RadioButton)findViewById(R.id.trainer_login);
        user_hint=(TextView) findViewById(R.id.user_hint);
        register_link=(TextView) findViewById(R.id.register_link);
        submit.setOnClickListener(this);
        register_link.setOnClickListener(this);

        mTestdb=new MockTestDb(getApplicationContext());

        if(!TestUtil.getInstance().getUser(this).equalsIgnoreCase("")){
            TestUtil.getInstance().saveUser(getApplicationContext(),uname_txt);
            Intent testintent=new Intent(this,TestActivity.class);
            startActivity(testintent);
            finish();
        }

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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                 uname_txt=uname.getText().toString();
                 pass_txt=password.getText().toString();
                 user_type="";

                if(student.isChecked())
                    user_type="S";
                if(trainer.isChecked())
                    user_type="T";

                if(validateUeer()){
                    if(action_type==1) {
                        if (mTestdb.checkUser(uname_txt).getCount() > 0) {
                            usernameWrapper.setErrorEnabled(true);
                            usernameWrapper.setError("User Already Exist");
                        }else {
                            mTestdb.registerUser(uname_txt, pass_txt, user_type);
                            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            register_link.performLongClick();
                        }
                    }else{
                        Cursor c=mTestdb.checkUser(uname_txt);
                        if(c.getCount()>0){
                            while(c.moveToNext()){
                                db_pass=c.getString(c.getColumnIndex(MockTestDb.TestDbHelper._PASS));
                            }


                        }else{
                            Toast.makeText(this, "User not Exist", Toast.LENGTH_SHORT).show();
                        }
                        if (db_pass.equalsIgnoreCase(pass_txt)) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                            TestUtil.getInstance().saveUser(getApplicationContext(),uname_txt);
                            Intent testintent=new Intent(this,TestActivity.class);
                            startActivity(testintent);
                            finish();
                        }else {
                            password.setText("");
                            passwordWrapper.setErrorEnabled(true);
                            passwordWrapper.setError("Password Incorrect");
                        }

                    }
                }
            break;
            case R.id.register_link:
                uname.setText("");
                password.setText("");
                if(action_type!=1){
                    submit.setText("Register");
                    action_type=1;
                    user_hint.setText(getResources().getString(R.string.usertype_registration_hint));
                    register_link.setText("Already have account Login Here!");
                }else{
                    submit.setText("Login");
                    action_type=0;
                    user_hint.setText(getResources().getString(R.string.usertype_login_hint));
                    register_link.setText(getResources().getString(R.string.register_hint));
                }
                break;
        }
    }

    private boolean validateUeer(){
        boolean result=true;
        if (user_type.equalsIgnoreCase("")) {
            Toast.makeText(this, "Select Type of user", Toast.LENGTH_SHORT).show();
            result=false;
        }
        else if (uname.getText().toString().equalsIgnoreCase("")) {
            usernameWrapper.setErrorEnabled(true);
            usernameWrapper.setError("Enter Username");
            result=false;
        } else if (pass_txt.equalsIgnoreCase("")) {
            passwordWrapper.setErrorEnabled(true);
            passwordWrapper.setError("Enter Password");
            result=false;
        }
        return result;
    }
}

package com.mocktest;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mocktest.database.MockTestDb;
import com.mocktest.pojo.QuestionList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    TextView _tv,question,question_cnt,score,answered_txt,unans_txt,dash_link;
    private ArrayList<QuestionList> questions;
    private RadioButton ans1,ans2,ans3,ans4;
    private int answered,unanswered,answered_correct,selected_ans,question_index,q_no=0;
    private Button submit,clear,skip;
    private LinearLayout opt_buttons,questions_layout,report;

    private MockTestDb mockTestDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        _tv = (TextView) findViewById( R.id.timer );
        question = (TextView) findViewById( R.id.question);
        score = (TextView) findViewById( R.id.score);
        answered_txt = (TextView) findViewById( R.id.answered);
        unans_txt = (TextView) findViewById( R.id.unanswered);
        question_cnt = (TextView) findViewById( R.id.question_cnt);
        dash_link = (TextView) findViewById( R.id.dash_link);
        ans1 = (RadioButton) findViewById( R.id.ans1);
        ans2 = (RadioButton) findViewById( R.id.ans2);
        ans3 = (RadioButton) findViewById( R.id.ans3);
        ans4 = (RadioButton) findViewById( R.id.ans4);
        submit = (Button) findViewById( R.id.submit);
        clear = (Button) findViewById( R.id.clear);
        skip = (Button) findViewById( R.id.skip);

        opt_buttons = (LinearLayout) findViewById( R.id.opt_buttons);
        questions_layout = (LinearLayout) findViewById( R.id.questions_layout);
        report = (LinearLayout) findViewById( R.id.report);

        skip.setOnClickListener(this);
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);
        dash_link.setOnClickListener(this);
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
       //
        mockTestDb=new MockTestDb(getApplicationContext());

        Cursor ques=mockTestDb.getQuestions();
        questions=new ArrayList<>();
        if(ques.getCount()>0) {
            while (ques.moveToNext()) {

                QuestionList q_list = new QuestionList();
                q_list.question = ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._QUESTION));
                q_list.opt1 = ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION1));
                q_list.opt2 = ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION2));
                q_list.opt3 = ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION3));
                q_list.opt4 = ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION4));
                q_list.answer = ques.getInt(ques.getColumnIndex(MockTestDb.TestDbHelper._ANSWER));
                questions.add(q_list);
            }
        }else{
            mockTestDb.setQuestions();
            Cursor ques1=mockTestDb.getQuestions();
            if(ques1.getCount()>0) {
                while (ques1.moveToNext()) {

                    QuestionList q_list = new QuestionList();
                    q_list.question = ques1.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._QUESTION));
                    q_list.opt1 = ques1.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION1));
                    q_list.opt2 = ques1.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION2));
                    q_list.opt3 = ques1.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION3));
                    q_list.opt4 = ques1.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION4));
                    q_list.answer = ques1.getInt(ques.getColumnIndex(MockTestDb.TestDbHelper._ANSWER));
                    questions.add(q_list);
                }
            }
        }
        Log.d("ques", String.valueOf(ques.getCount()));

       /* while(questions.size() > 0) {
            int question_index = rand.nextInt(questions.size());
           // System.out.println("Selected: "+questions.remove(index));

        }*/
        move_to_next_question(get_nextquestion());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.skip:
                move_to_next_question(get_nextquestion());
                unanswered+=1;
                break;
            case R.id.submit:
                if(ans1.isChecked()){
                    selected_ans=1;
                    answered+=1;
                }else if(ans2.isChecked()){
                    selected_ans=2;
                    answered+=1;
                }else if(ans3.isChecked()){
                    selected_ans=3;
                    answered+=1;
                }else if(ans4.isChecked()){
                    selected_ans=4;
                    answered+=1;
                }else {
                    unanswered+=1;
                }
                ans1.setChecked(false);
                ans2.setChecked(false);
                ans3.setChecked(false);
                ans4.setChecked(false);
                if(selected_ans==questions.get(question_index).answer){
                answered_correct+=1;
                }
                if(q_no<4)
                    move_to_next_question(get_nextquestion());
                    else
                    complete_test();
                break;
            case R.id.clear:
                ans1.setChecked(false);
                ans2.setChecked(false);
                ans3.setChecked(false);
                ans4.setChecked(false);
                break;
            case R.id.dash_link:
                Intent dash=new Intent(TestActivity.this,DashBoard.class);
                startActivity(dash);
                finish();
                break;
        }
    }

    private int get_nextquestion(){
        Random rand = new Random();
         question_index=0;
        if(questions.size() > 0) {
            question_index = rand.nextInt(questions.size()-1);
            questions.remove(question_index);
        }
        return question_index;
    }

    private void move_to_next_question(int q_index){
        q_no+=1;
        Log.d("qindx", String.valueOf(q_index));
        question_cnt.setText("Question "+q_no);
        question.setText(questions.get(q_index).question);
        ans1.setText(questions.get(q_index).opt1);
        ans2.setText(questions.get(q_index).opt2);
        ans3.setText(questions.get(q_index).opt3);
        ans4.setText(questions.get(q_index).opt4);

    }

    private void complete_test(){
        opt_buttons.setVisibility(View.GONE);
        questions_layout.setVisibility(View.GONE);
        _tv.setVisibility(View.GONE);
        report.setVisibility(View.VISIBLE);
        score.setText("Score: "+answered_correct+"/"+q_no);
        answered_txt.setText("Answered: "+answered);
        unans_txt.setText("Unanswered: "+unanswered);
        question_cnt.setText("Report");
        mockTestDb.insertTestdet(TestUtil.getInstance().getUser(this),answered_correct,System.currentTimeMillis()/1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_cancel:
                Intent testintent=new Intent(this,DashBoard.class);
                startActivity(testintent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

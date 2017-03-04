package com.mocktest;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mocktest.pojo.QuestionList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    TextView _tv,question;
    private ArrayList<QuestionList> questions;
    private RadioButton ans1,ans2,ans3,ans4;
    private int answered,unanswered,answered_correct,selected_ans,question_index,q_no;
    private Button submit,clear,skip;
    private LinearLayout opt_buttons,questions_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

               _tv = (TextView) findViewById( R.id.timer );
        question = (TextView) findViewById( R.id.question);
        ans1 = (RadioButton) findViewById( R.id.ans1);
        ans1 = (RadioButton) findViewById( R.id.ans2);
        ans1 = (RadioButton) findViewById( R.id.ans3);
        ans1 = (RadioButton) findViewById( R.id.ans4);
        submit = (Button) findViewById( R.id.submit);
        clear = (Button) findViewById( R.id.clear);
        skip = (Button) findViewById( R.id.skip);

        opt_buttons = (LinearLayout) findViewById( R.id.opt_buttons);
        questions_layout = (LinearLayout) findViewById( R.id.questions_layout);

        skip.setOnClickListener(this);
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);
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
/*                Cursor ques=mTestdb.getQuestions();
        questions=new ArrayList<>();
        while(ques.moveToNext()){

            QuestionList q_list=new QuestionList();
            q_list.question=ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._QUESTION));
            q_list.opt1=ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION1));
            q_list.opt2=ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION2));
            q_list.opt3=ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION3));
            q_list.opt4=ques.getString(ques.getColumnIndex(MockTestDb.TestDbHelper._OPTION4));
            q_list.answer=ques.getInt(ques.getColumnIndex(MockTestDb.TestDbHelper._ANSWER));
            questions.add(q_list);
        }
        Log.d("ques", String.valueOf(ques.getCount()));

        Random rand = new Random();
        while(questions.size() > 0) {
            int question_index = rand.nextInt(questions.size());
           // System.out.println("Selected: "+questions.remove(index));

        }
        move_to_next_question(get_nextquestion());

        */

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.skip:
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

                /*
                if(selected_ans==questions.get(question_index).answer){
                answered_correct+=1;
                }
                if(q_no!=questions.size())
                    move_to_next_question(get_nextquestion());
                    else
                    complete_test();
                 */
                break;
            case R.id.clear:
                break;
        }
    }

    private int get_nextquestion(){
        Random rand = new Random();
         question_index=0;
        if(questions.size() > 0) {
            question_index = rand.nextInt(questions.size());
            questions.remove(question_index);
        }
        return question_index;
    }

    private void move_to_next_question(int q_index){
        q_no+=1;
        question.setText(questions.get(q_index).question);
        ans1.setText(questions.get(q_index).opt1);
        ans2.setText(questions.get(q_index).opt2);
        ans3.setText(questions.get(q_index).opt3);
        ans4.setText(questions.get(q_index).opt4);

    }

    private void complete_test(){
        opt_buttons.setVisibility(View.GONE);
        questions_layout.setVisibility(View.GONE);
    }
}

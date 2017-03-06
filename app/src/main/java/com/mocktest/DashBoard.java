package com.mocktest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mocktest.database.MockTestDb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static android.R.attr.data;

public class DashBoard extends AppCompatActivity {
    TableLayout scroll;
    MockTestDb mockTestDb;
    LinearLayout no_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mockTestDb=new MockTestDb(getApplicationContext());
         scroll = (TableLayout) this.findViewById(R.id.testlist);
        no_test = (LinearLayout) this.findViewById(R.id.no_test);

        Cursor c=mockTestDb.getTests();
        if(c.getCount()>0){
            //test available
            TableRow tr1 = new TableRow(this);
            TableLayout.LayoutParams params=new TableLayout.LayoutParams( TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tr1.setLayoutParams(params);
            TextView textview_fields = new TextView(this);
            textview_fields.setText("User       Score       Date Taken");

            tr1.addView(textview_fields);

            scroll.addView(tr1, params);
            while(c.moveToNext()){

                TableRow tr2 = new TableRow(this);
                tr1.setLayoutParams(params);
                TextView textview = new TextView(this);
                String user=c.getString(c.getColumnIndex(MockTestDb.TestDbHelper._USER));
                int score=c.getInt(c.getColumnIndex(MockTestDb.TestDbHelper._SCORE));
                long date_db=c.getLong(c.getColumnIndex(MockTestDb.TestDbHelper._USER));

                Date date = new Date(date_db);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String formattedDate = sdf.format(date);
                textview.setText(user+"         "+String.valueOf(score)+"           "+formattedDate);

                tr2.addView(textview);

                scroll.addView(tr2, params);
            }

        }else{
            scroll.setVisibility(View.GONE);
            no_test.setVisibility(View.VISIBLE);
        }




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_test:
                Intent testintent=new Intent(this,TestActivity.class);
                startActivity(testintent);
                finish();
                return true;
            case R.id.menu_logout:
                TestUtil.getInstance().saveUser(this,"");
                Intent logoutintent=new Intent(this,LoginActivity.class);
                startActivity(logoutintent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

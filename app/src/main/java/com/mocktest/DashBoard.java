package com.mocktest;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mocktest.database.MockTestDb;

import static android.R.attr.data;

public class DashBoard extends AppCompatActivity {
    TableLayout scroll;
    MockTestDb mockTestDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mockTestDb=new MockTestDb(getApplicationContext());
         scroll = (TableLayout) this.findViewById(R.id.testlist);

        Cursor c=mockTestDb.getTests();
        if(c.getCount()>0){
            //test available
            while(c.moveToNext()){

                TableRow tr1 = new TableRow(this);
                TableLayout.LayoutParams params=new TableLayout.LayoutParams( TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                TableLayout.LayoutParams params1=new TableLayout.LayoutParams( TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);


                tr1.setLayoutParams(params);
                params1.setMargins(0,0,20,10);
                TextView textview = new TextView(this);
                textview.setText("data");
                //textview.setLayoutParams(params1);
                TextView textview1 = new TextView(this);
                textview1.setText("data");
                //textview1.setLayoutParams(params1);
                TextView textview2 = new TextView(this);
                textview2.setText("data");
               // textview2.setLayoutParams(params1);
//textview.getTextColors(R.color.)
//                textview.setTextColor(Color.YELLOW);
                tr1.addView(textview);


                tr1.addView(textview1);
                tr1.addView(textview2);
                scroll.addView(tr1, params);
            }

        }else{
            scroll.setVisibility(View.GONE);
        }




    }
}

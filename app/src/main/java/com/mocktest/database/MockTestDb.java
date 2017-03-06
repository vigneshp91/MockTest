package com.mocktest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MockTestDb {

    private TestDbHelper mHelper;
    private Context ctx;

    public  MockTestDb(Context context){
        this.ctx=context;
        mHelper=new TestDbHelper(ctx);
    }

    public void registerUser(String uname, String pass,String type){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        ContentValues data=new ContentValues();
        data.put(TestDbHelper._USER,uname);
        data.put(TestDbHelper._PASS,pass);
        data.put(TestDbHelper._USER_TYPE,type);
        db.insert(TestDbHelper.MOCKTEST_USERTABLE,null,data);
    }

    public void insertTestdet(String user,int score, long time){
        SQLiteDatabase db=mHelper.getReadableDatabase();
        ContentValues data=new ContentValues();
        data.put(TestDbHelper._USER,user);
        data.put(TestDbHelper._SCORE,score);
        data.put(TestDbHelper._TIME,time);
        db.insert(TestDbHelper.MOCKTEST_TESTTABLE,null,data);
    }

    public Cursor checkUser(String uname){
        SQLiteDatabase db= mHelper.getWritableDatabase();
        String[] columns={TestDbHelper._USER,TestDbHelper._PASS};
        String args[]={uname};
        return  db.query(TestDbHelper.MOCKTEST_USERTABLE,columns,TestDbHelper._USER+" = ?",args,null,null,null);
    }


    public Cursor getQuestions(){
        SQLiteDatabase db= mHelper.getWritableDatabase();
        String[] columns={TestDbHelper._QUESTION,TestDbHelper._OPTION1,TestDbHelper._OPTION2,TestDbHelper._OPTION3,TestDbHelper._OPTION4,TestDbHelper._ANSWER};

        return  db.query(TestDbHelper.MOCKTEST_QUESTABLE,columns,null,null,null,null,null,"0,5");
    }

    public Cursor getTests(){
        SQLiteDatabase db= mHelper.getWritableDatabase();
        String[] columns={TestDbHelper._USER,TestDbHelper._SCORE,TestDbHelper._TIME};

        return  db.query(TestDbHelper.MOCKTEST_TESTTABLE,columns,null,null,null,null,null);
    }

    public void setQuestions(){
        SQLiteDatabase db= mHelper.getWritableDatabase();
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Grand Central Terminal, Park Avenue, New York is the world's\",\"largest railway station\",\"highest railway station\",\"longest railway station\",\"None of the above\",1)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Entomology is the science that studies\",\"Behavior of human beings\",\"Insects\",\"The origin and history of technical and scientific terms\",\"The formation of rocks\",2)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Eritrea, which became the 182nd member of the UN in 1993, is in the continent of\",\"Asia\",\"Africa\",\"Europe\",\"Australia\",2)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Garampani sanctuary is located at\",\"Junagarh, Gujarat\",\"Diphu, Assam\",\"Kohima, Nagaland\",\"Gangtok, Sikkim\",2)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"For which of the following disciplines is Nobel Prize awarded?\",\"Physics and Chemistry\",\"Physiology or Medicine\",\"Literature, Peace and Economics\",\"All of the above\",4)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Hitler party which came into power in 1933 is known as\",\"Labour Party\",\"Nazi Party\",\"Ku-Klux-Klan\",\"Democratic Party\",2)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"FFC stands for\",\"Foreign Finance Corporation\",\"Film Finance Corporation\",\"Federation of Football Council\",\"None of the above\",2)");
        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Fastest shorthand writer was\",\"Dr. G. D. Bist\",\"J.R.D. Tata\",\"J.M. Tagore\",\"Khudada Khan\",1)");
    }

    public static class TestDbHelper extends SQLiteOpenHelper {

        public static final Integer DB_VERSION=8;
        public static final String MOCKTEST_DB="MOCKTEST_DB";
        public static final String MOCKTEST_USERTABLE="USER_TBL";
        public static final String MOCKTEST_QUESTABLE="QUES_TBL";
        public static final String MOCKTEST_TESTTABLE="TEST_TBL";
        public static final String _USER="USER";
        public static final String _PASS="PASSWORD";
        public static final String _USER_TYPE="USER_TYPE";
        public static final String _QUESTION="QUESTION";
        public static final String _OPTION1="OPTION1";
        public static final String _OPTION2="OPTION2";
        public static final String _OPTION3="OPTION3";
        public static final String _OPTION4="OPTION4";
        public static final String _ANSWER="ANSWER";
        public static final String _SCORE="SCORE";
        public static final String _TIME="TIME";

        String CREATE_TABLE_FOR_USER="CREATE TABLE "+MOCKTEST_USERTABLE+" ("+_USER+" VARCHAR, "+_PASS+" VARCHAR, "+_USER_TYPE
        + " VARCHAR )";

        String CREATE_TABLE_QUESTIONS="CREATE TABLE "+MOCKTEST_QUESTABLE+" ("+_QUESTION+" VARCHAR, "+_OPTION1+" VARCHAR, "+_OPTION2+
                " VARCHAR, "+_OPTION3+" VARCHAR, "+_OPTION4+" VARCHAR, "+_ANSWER+" INTEGER )";

        String CRET_TABLE_TEST="CREATE TABLE "+MOCKTEST_TESTTABLE+" ("+_USER+" VARCHAR, "+_SCORE+" INTEGER, "+_TIME+" LONG )";

        String DROP_TABLE_FOR_USER="DROP TABLE IF EXISTS "+MOCKTEST_USERTABLE;
        String DROP_TABLE_FOR_QUESTION="DROP TABLE IF EXISTS "+MOCKTEST_QUESTABLE;
        String DROP_TABLE_FOR_TEST="DROP TABLE IF EXISTS "+MOCKTEST_TESTTABLE;

        public TestDbHelper(Context context) {
            super(context, MOCKTEST_DB, null, DB_VERSION);
            Log.e("db",CREATE_TABLE_FOR_USER);
            Log.e("db",CREATE_TABLE_QUESTIONS);
            Log.e("db",CRET_TABLE_TEST);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(CREATE_TABLE_FOR_USER);
                sqLiteDatabase.execSQL(CREATE_TABLE_QUESTIONS);
                sqLiteDatabase.execSQL(CRET_TABLE_TEST);
            }catch (Exception e){}

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                sqLiteDatabase.execSQL(DROP_TABLE_FOR_USER);
                sqLiteDatabase.execSQL(DROP_TABLE_FOR_QUESTION);
                sqLiteDatabase.execSQL(DROP_TABLE_FOR_TEST);
                onCreate(sqLiteDatabase);
            }catch (Exception e){}

        }
    }
}

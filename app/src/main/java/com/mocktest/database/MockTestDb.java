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

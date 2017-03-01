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

    public Cursor checkUser(String uname){
        SQLiteDatabase db= mHelper.getWritableDatabase();
        String[] columns={TestDbHelper._USER,TestDbHelper._PASS};
        String args[]={uname};
        return  db.query(TestDbHelper.MOCKTEST_USERTABLE,columns,TestDbHelper._USER+" = ?",args,null,null,null);
    }

    public static class TestDbHelper extends SQLiteOpenHelper {

        public static final Integer DB_VERSION=5;
        public static final String MOCKTEST_DB="MOCKTEST_DB";
        public static final String MOCKTEST_USERTABLE="USER_TBL";
        public static final String _USER="USER";
        public static final String _PASS="PASSWORD";
        public static final String _USER_TYPE="USER_TYPE";

        String CREATE_TABLE_FOR_USER="CREATE TABLE "+MOCKTEST_USERTABLE+" ("+_USER+" VARCHAR, "+_PASS+" VARCHAR, "+_USER_TYPE
        + " VARCHAR )";
        String DROP_TABLE_FOR_USER="DROP TABLE IF EXISTS "+MOCKTEST_USERTABLE;

        public TestDbHelper(Context context) {
            super(context, MOCKTEST_DB, null, DB_VERSION);
            Log.e("db",CREATE_TABLE_FOR_USER);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(CREATE_TABLE_FOR_USER);
            }catch (Exception e){}

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                sqLiteDatabase.execSQL(DROP_TABLE_FOR_USER);
                onCreate(sqLiteDatabase);
            }catch (Exception e){}

        }
    }
}

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

        return  db.query(TestDbHelper.MOCKTEST_TESTTABLE,columns,null,null,null,null,TestDbHelper._SCORE+" desc");
    }
    public Cursor getUserTests(String uname){
        SQLiteDatabase db= mHelper.getWritableDatabase();
        String[] columns={TestDbHelper._USER,TestDbHelper._SCORE,TestDbHelper._TIME};
        String args[]={uname};
        return  db.query(TestDbHelper.MOCKTEST_TESTTABLE,columns,TestDbHelper._USER+" = ?",args,null,null,TestDbHelper._SCORE+" desc");
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

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Epsom (England) is the place associated with\",\"Horse racing\",\"Polo\",\"Shooting\",\"Snooker\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"First human heart transplant operation conducted by Dr. Christiaan Barnard on Louis Washkansky, was conducted in\",\"1967\",\"1968\",\"1958\",\"1922\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Galileo was an Italian astronomer who\",\"developed the telescope\",\"discovered four satellites of Jupiter\",\"discovered that the movement of pendulum produces a regular time measurement\",\"All of the above\",4)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Golf player Vijay Singh belongs to which country?\",\"USA\",\"Fiji\",\"India\",\"UK\",2)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"First Afghan War took place in\",\"1839\",\"1843\",\"1833\",\"1848\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Gulf cooperation council was originally formed by\",\"Bahrain, Kuwait, Oman, Qatar, Saudi Arabia and United Arab Emirates\",\"Second World Nations\",\"Third World Nations\",\"Fourth World Nations\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"First China War was fought between\",\"China and Britain\",\"China and France\",\"China and Egypt\",\"China and Greek\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Guwahati High Court is the judicature of\",\"Nagaland\",\"Arunachal Pradesh\",\"Assam\",\"All of the above\",4)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Friction can be reduced by changing from\",\"sliding to rolling\",\"rolling to sliding\",\"potential energy to kinetic energy\",\"dynamic to static\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Fire temple is the place of worship of which of the following religion?\",\"Taoism\",\"Judaism\",\"Zoroastrianism (Parsi Religion)\",\"Shintoism\",3)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Film and TV institute of India is located at\",\"Pune (Maharashtra)\",\"Rajkot (Gujarat)\",\"Pimpri (Maharashtra)\",\"Perambur (Tamilnadu)\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Headquarters of UNO are situated at\",\"New York USA\",\"Hague (Netherlands)\",\"Geneva\",\"Paris\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"First International Peace Congress was held in London in\",\"1564 AD\", \"1798 AD\",\"1843 AD\",\"1901 AD\",3)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"For seeing objects at the surface of water from a submarine under water, the instrument used is\",\"kaleidoscope\",\"periscope\",\"spectroscope\",\"telescope\",2)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Dr. Zakir Hussain was\",\"the first Muslim president of India\",\"first vice president of India\",\"first president of Indian National Congress\",\"first speaker of Lok Sabha\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"G-15 is an economic grouping of\",\"First World Nations\",\"Second World Nations\",\"Third World Nations\",\"Fourth World Nations\",3)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Fathometer is used to measure\",\"Earthquakes\",\"Rainfall\",\"Ocean depth\",\"Sound intensity\",3)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Georgia, Uzbekistan and Turkmenistan became the members of UNO in\",\"1991\",\"1992\",\"1993\",\"1994\",2)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"During World War II, when did Germany attack France?\",\"1940\",\"1941\",\"1942\",\"1943\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Frederick Sanger is a twice recipient of the Nobel Prize for\",\"Chemistry in 1958 and 1980\",\"Physics in 1956 and 1972\",\"Chemistry in 1954 and Peace in 1962\",\"Physics in 1903 and Chemistry in 1911\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"GNLF stands for\",\"Gorkha National Liberation Front\",\"Gross National Liberation Form\",\"Both option A and B\",\"None of the above\",1)");

        db.execSQL("INSERT INTO QUES_TBL (QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,ANSWER) VALUES (\"Excessive secretion from the pituitary gland in the children results in\",\"increased height\",\"retarded growth\",\"weakening of bones\",\"None of the above\",1)");
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

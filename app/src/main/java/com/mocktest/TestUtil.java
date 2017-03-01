package com.mocktest;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class TestUtil {

    private static TestUtil instance=new TestUtil();
    private  final String MyPREFERENCES = "MockPrefs" ;
    private  final String UNAME_KEY = "UNAME" ;


    public static TestUtil getInstance(){
        if(instance==null)
            instance=new TestUtil();
        return instance;

    }

    public void saveUser(Context ctx, String uname){
        SharedPreferences pref=ctx.getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(UNAME_KEY,uname).apply();
    }

    public String getUser(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(UNAME_KEY,"");
    }


}

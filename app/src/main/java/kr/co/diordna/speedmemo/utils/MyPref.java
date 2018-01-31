package kr.co.diordna.speedmemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ryans on 2018-01-31.
 */

public class MyPref {

    private final String PREF_KEY = "PREF_KEY";
    private Context mContext;
    private SharedPreferences mPref;

    private static MyPref instance = null;

    public static MyPref getInstance() {
        if (instance == null) {
            synchronized (MyPref.class) {
                if (instance == null) {
                    instance = new MyPref();
                }
            }
        }

        return instance;
    }

    private MyPref() {}

    public void init(Context c) {
        mContext = c.getApplicationContext();
        mPref = mContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean b) {
        SharedPreferences.Editor edit = mPref.edit();
        edit.putBoolean(key, b);
        edit.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

//    public MyPref(Context c) {
//        mContext = c.getApplicationContext();
//    }
}

package kr.co.diordna.speedmemo;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import kr.co.diordna.speedmemo.utils.MyPref;

/**
 * Created by ryans on 2018-01-21.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        MyPref.getInstance().init(this);
    }
}

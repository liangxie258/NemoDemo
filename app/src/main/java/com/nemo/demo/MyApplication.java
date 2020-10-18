package com.nemo.demo;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import com.gldcircle.emoji.EmojiManager;

public class MyApplication extends Application {

    public static  Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        float density = displayMetrics.density;
        float scaledDensity = displayMetrics.scaledDensity;
        EmojiManager.init(this);
//        Log.i(Constants.TAG, "\nwidthPixels:" + widthPixels + "   \nheightPixels" + heightPixels + "     \ndensity" + density + "    \nscaledDensity" + scaledDensity);
    }


}

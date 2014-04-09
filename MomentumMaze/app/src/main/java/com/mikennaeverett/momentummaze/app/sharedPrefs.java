package com.mikennaeverett.momentummaze.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by sduvall10 on 4/8/14.
 */
public class sharedPrefs {
    void savePrefs(){
        SharedPreferences settings = getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
}

package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by sduvall10 on 4/8/14.
 */
public class SharedPrefs {
    private Context context;
    private int highUnlocked;
    //private SharedPreferences settings;

    public SharedPrefs(Activity context) {
        this.context = context;

    }

    void savePrefs(){
        SharedPreferences settings = context.getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("highestUnlockedLevel", highUnlocked);
        editor.commit();
    }

    void loadPrefs(){
        SharedPreferences settings = context.getSharedPreferences("settings", 0);
        highUnlocked = settings.getInt("highestUnlockedLevel", highUnlocked);
    }

    int getHighUnlocked(){
        return highUnlocked;
    }

    void setHighUnlocked(int hiUn){
        highUnlocked = hiUn;
    }
}

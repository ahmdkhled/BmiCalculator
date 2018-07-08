package com.ahmedkhaled.bmicalculator.backend;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ahmed Khaled on 9/24/2017.
 */

public class Setting  {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static String PREF="PREF";

    public Setting(Context context) {
        preferences=context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        editor=preferences.edit();
    }

    public void setFirstTIme(boolean state){
        editor.putBoolean("IsFirstTime",state);
        editor.commit();
    }

    public void saveData(String weight, String height){
        editor.putString("WEIGHT",weight);
        editor.putString("HEIGHT",height);
        editor.commit();
    }

    public boolean isFirstTime(){
        return preferences.getBoolean("IsFirstTime",true);
    }


    public String getWeight(){
        return preferences.getString("WEIGHT","error");
    }
    public String getHeight(){return preferences.getString("HEIGHT","error");
    }

}

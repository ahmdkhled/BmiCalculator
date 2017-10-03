package com.ahmedkhaled.bmicalculator;

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

    void setFirstTIme(boolean state){
        editor.putBoolean("IsFirstTime",state);
        editor.commit();
    }

    public void saveData(String age, String gender, String weight, String height){
        editor.putString("AGE",age);
        editor.putString("GENDER",gender);
        editor.putString("WEIGHT",weight);
        editor.putString("HEIGHT",height);
        editor.commit();
    }

    boolean isFirstTime(){
        return preferences.getBoolean("IsFirstTime",true);
    }

    public String getAge(){
        return preferences.getString("AGE","error");
    }
    public String getGender(){
        return preferences.getString("GENDER","error");
    }
    public String getWeight(){
        return preferences.getString("WEIGHT","error");
    }
    public String getHeight(){return preferences.getString("HEIGHT","error");
    }

}

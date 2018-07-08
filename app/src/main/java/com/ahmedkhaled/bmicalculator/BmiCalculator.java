package com.ahmedkhaled.bmicalculator;

import android.content.Context;

import com.ahmedkhaled.bmicalculator.backend.Setting;

/**
 * Created by Ahmed Khaled on 9/25/2017.
 */

public class BmiCalculator {

    private float Bmi;

    public BmiCalculator(Context context) {
        Setting setting=new Setting(context);
        float weight= Float.parseFloat(setting.getWeight());
        float lenght= Float.parseFloat(setting.getHeight());
        Bmi=calulateBmi(weight,lenght);

    }

    private float  calulateBmi(float weight, float height){
        return weight/(height*height/10000);
    }

    public String getBmiClass(){
        if (getBmi()<=18.5){
            return "Underweight";
        }
        else if (getBmi()>18.5&&getBmi()<=24.9){
            return "Normal";
        }else if (getBmi()>=25&&getBmi()<=29.9){
            return "Overweight";
        }else if (getBmi()>=30){
            return "Obese";
        }else {
            return "error";
        }
    }


    public void setBmi(int bmi) {
        Bmi = bmi;
    }

    public float getBmi() {
        return Bmi;
    }
}

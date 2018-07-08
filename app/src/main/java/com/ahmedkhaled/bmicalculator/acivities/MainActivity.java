package com.ahmedkhaled.bmicalculator.acivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmedkhaled.bmicalculator.CustomViewPager;
import com.ahmedkhaled.bmicalculator.adapters.IntroPagerAdapter;
import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.backend.Setting;
import com.ahmedkhaled.bmicalculator.fragments.FragmentThree;
import com.ahmedkhaled.bmicalculator.fragments.FragmentTwo;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements FragmentTwo.OnDataCollectedListener,FragmentThree.OnDataCollectedListener {

    public CustomViewPager viewPager;
    Setting setting;
    String weight,hight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setting=new Setting(this);
        if (!setting.isFirstTime()){
            Intent intent=new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
            finish();
        }
        CircleIndicator indicator =  findViewById(R.id.indicator);
        viewPager= findViewById(R.id.viewpager);
        IntroPagerAdapter introPagerAdapter=new IntroPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(introPagerAdapter);
        indicator.setViewPager(viewPager);

    }



    @Override
    public void onSecondDataCollectedListener(String height) {
        Intent intent=new Intent(getApplicationContext(),Home.class);
        setting.setFirstTIme(false);
        intent.putExtra("firstTime","firstTime");
        setting.saveData(weight,height);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDataCollectedListener(String weight) {
        this.weight=weight;
    }
}

package com.ahmedkhaled.bmicalculator;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.fragments.FragmentThree;
import com.ahmedkhaled.bmicalculator.fragments.FragmentTwo;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements FragmentTwo.OnDataCollectedListener,FragmentThree.OnDataCollectedListener {

    public CustomViewPager viewPager;
    Setting setting;
    String age,gender;
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
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewPager=(CustomViewPager) findViewById(R.id.viewpager);
        IntroPagerAdapter introPagerAdapter=new IntroPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(introPagerAdapter);
        indicator.setViewPager(viewPager);

    }

    @Override
    public void onDataCollectedListener(String age, String gender) {
        this.age=age;
        this.gender=gender;
        Log.d("TAG","result  "+gender+"  "+age );
    }




    @Override
    public void onSecondDataCollectedListener(String weight, String height) {
        Intent intent=new Intent(getApplicationContext(),Home.class);
        setting.setFirstTIme(false);
        intent.putExtra("firstTime","firstTime");
        setting.saveData(age,gender,weight,height);
        startActivity(intent);
        finish();
    }
}

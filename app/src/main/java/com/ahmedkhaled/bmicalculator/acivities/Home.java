package com.ahmedkhaled.bmicalculator.acivities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.backend.Setting;
import com.ahmedkhaled.bmicalculator.fragments.HistoryFragment;
import com.ahmedkhaled.bmicalculator.fragments.ResultFragment;
import com.ahmedkhaled.bmicalculator.fragments.SettingFragment;

public class Home extends AppCompatActivity{

    public BottomNavigationView bottomNavigationView;
    ResultFragment homeFragment;
    HistoryFragment historyFragment;
    SettingFragment settingFragment;
    Setting setting;
    String homeFragment_TAG="HOME_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView= findViewById(R.id.bottom_nav);
        homeFragment=new ResultFragment();
        historyFragment=new HistoryFragment();
        settingFragment=new SettingFragment();
        setting=new Setting(getApplicationContext());

        showFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Result:
                        addFragment(homeFragment);
                        break;
                    case R.id.History:
                        addFragment(historyFragment);
                        break;
                    case R.id.Settings:
                        addFragment(settingFragment);
                        break;
                }
                return true;
            }
        });
    }

    void showFragment(){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, homeFragment, homeFragment_TAG)
                    .commit();
    }

    void addFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment).commit();
    }




}

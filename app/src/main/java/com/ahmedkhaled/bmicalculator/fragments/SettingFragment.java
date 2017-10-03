package com.ahmedkhaled.bmicalculator.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.BmiCalculator;
import com.ahmedkhaled.bmicalculator.HistoryDatabase;
import com.ahmedkhaled.bmicalculator.Home;
import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.Setting;

/**
 * Created by Ahmed Khaled on 9/25/2017.
 */

public class SettingFragment extends Fragment {

    EditText Weight,Height,Age;
    RadioButton male,female;
    Button save;
    String gender;
    Setting setting;
    BmiCalculator bmiCalculator;
    HistoryDatabase historyDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_setting,container,false);
        historyDatabase=new HistoryDatabase(getActivity());
        bmiCalculator=new BmiCalculator(getActivity());
        setting=new Setting(getActivity());
        Weight= (EditText) view.findViewById(R.id.weightSetting);
        Height= (EditText) view.findViewById(R.id.heightSetting);
        Age= (EditText) view.findViewById(R.id.ageSetting);
        male= (RadioButton) view.findViewById(R.id.maleSetting);
        female= (RadioButton) view.findViewById(R.id.femaleSetting);
        save= (Button) view.findViewById(R.id.save);

        Age.setText(setting.getAge());
        Weight.setText(setting.getWeight());
        Height.setText(setting.getHeight());
        gender=setting.getGender();
        if (gender.equals("male")){male.setChecked(true);}
        else if (gender.equals("female")){female.setChecked(true);}

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              if (b)gender="male";
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)gender="female";
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight=Weight.getText().toString();
                String height=Height.getText().toString();
                String age=Age.getText().toString();

                setting.saveData(age,gender,weight,height);
                historyDatabase.insertData(weight,height,age,String.format("%.1f",bmiCalculator.getBmi()));
                ((Home)getActivity()).bottomNavigationView.setSelectedItemId(R.id.Result);
                Toast.makeText(getActivity(),"data updated successfully ",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}

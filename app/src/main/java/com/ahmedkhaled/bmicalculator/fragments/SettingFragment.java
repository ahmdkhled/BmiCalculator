package com.ahmedkhaled.bmicalculator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.BmiCalculator;
import com.ahmedkhaled.bmicalculator.backend.HistoryDatabase;
import com.ahmedkhaled.bmicalculator.acivities.Home;
import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.backend.Setting;

/**
 * Created by Ahmed Khaled on 9/25/2017.
 */

public class SettingFragment extends Fragment {

    EditText Weight,Height;
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
        save= (Button) view.findViewById(R.id.save);

        Weight.setText(setting.getWeight());
        Height.setText(setting.getHeight());





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weight=Weight.getText().toString();
                String height=Height.getText().toString();

                setting.saveData(weight,height);
                historyDatabase.insertData(weight,height,String.format("%.1f",bmiCalculator.getBmi()));
                ((Home)getActivity()).bottomNavigationView.setSelectedItemId(R.id.Result);
                Toast.makeText(getActivity(),"data updated successfully ",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}

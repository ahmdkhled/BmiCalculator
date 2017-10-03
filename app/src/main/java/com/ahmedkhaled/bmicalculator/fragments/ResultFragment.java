package com.ahmedkhaled.bmicalculator.fragments;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.BmiCalculator;
import com.ahmedkhaled.bmicalculator.HistoryDatabase;
import com.ahmedkhaled.bmicalculator.Home;
import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.Setting;


/**
 * Created by Ahmed Khaled on 9/25/2017.
 */

public class ResultFragment extends Fragment {

    TextView result, bmiClass, weight, height;
    int progress;
    boolean firstTime = true;
    Handler handler;
    Runnable runnable;
    BmiCalculator bmiCalculator;
    Setting setting;
    HistoryDatabase historyDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);
        result = (TextView) view.findViewById(R.id.result);
        bmiClass = (TextView) view.findViewById(R.id.bmiClass);
        weight = (TextView) view.findViewById(R.id.weightInfo);
        height = (TextView) view.findViewById(R.id.heightInfo);

        bmiCalculator = new BmiCalculator(getActivity());
        setting = new Setting(getActivity());
        historyDatabase = new HistoryDatabase(getActivity());

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.getStringExtra("firstTime") != null && firstTime) {
            animateMe(0, (int) bmiCalculator.getBmi());
            firstTime = false;
        } else {
            showResult();
        }


        return view;
    }


    void animateMe(final int start, final int end) {
        handler = new Handler();
        progress = start;
        runnable = new Runnable() {
            @Override
            public void run() {
                if (progress > end) {
                    handler.removeCallbacks(runnable);
                    showResult();
                    saveData();
                } else {
                    result.setText("" + progress);
                    progress++;
                    handler.postDelayed(this, 75);
                }
            }
        };
        handler.post(runnable);
    }


    void showResult() {
        String finalResult = String.format("%.1f", bmiCalculator.getBmi());
        result.setText(finalResult);
        bmiClass.setText(bmiCalculator.getBmiClass());
        weight.setText("weight : " + setting.getWeight());
        height.setText("height : " + setting.getHeight());
    }

    void saveData() {
        String finalResult = String.format("%.1f", bmiCalculator.getBmi());

        historyDatabase.insertData(setting.getWeight(), setting.getHeight(), setting.getAge(), finalResult);

    }

}


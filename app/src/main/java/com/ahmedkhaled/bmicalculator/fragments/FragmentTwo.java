package com.ahmedkhaled.bmicalculator.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.CustomViewPager;
import com.ahmedkhaled.bmicalculator.MainActivity;
import com.ahmedkhaled.bmicalculator.R;

/**
 * Created by Ahmed Khaled on 9/13/2017.
 */

public class FragmentTwo extends Fragment  {
    EditText Age;
    String age;
    String gender="male";
    boolean scrolling=false;
    CustomViewPager viewPager;
    OnDataCollectedListener onDataCollectedListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        onDataCollectedListener= (OnDataCollectedListener) getActivity();
        final View view=inflater.inflate(R.layout.fragment_two,container,false);
        Age= (EditText) view.findViewById(R.id.Age);
        RadioButton male= (RadioButton) view.findViewById(R.id.male);
        final RadioButton female= (RadioButton) view.findViewById(R.id.female);
        viewPager=((MainActivity)getActivity()).viewPager;
        male.setChecked(true);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewPager.setEnabled(true);
                if (b){
                    gender="male";
                }
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                viewPager.setEnabled(true);
                if (b){
                    gender="female";
                }

            }
        });

        Age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                viewPager.setEnabled(true);

            }
        });
        Age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setEnabled(true);

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position==1){
                scrolling=true;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_DRAGGING ||scrolling){
                    if (viewPager.getCurrentItem()==1) {

                        if (isDataCompleted()){
                            viewPager.setEnabled(true);
                            Log.d("TAG","data is completed");
                            onDataCollectedListener.onDataCollectedListener(age,gender);
                        }else {
                            Toast.makeText(getActivity(),"please enter gender & age ",Toast.LENGTH_LONG).show();
                            viewPager.setEnabled(false);
                        }
                    }
                }
            }
        });

        return view;
    }

    boolean isDataCompleted(){
        age=Age.getText().toString();
        if (age.length()>0&&gender.length()>0){
            return true;
        }
        return false;
    }



    public interface OnDataCollectedListener {
        void onDataCollectedListener(String age,String gender);
    }
}

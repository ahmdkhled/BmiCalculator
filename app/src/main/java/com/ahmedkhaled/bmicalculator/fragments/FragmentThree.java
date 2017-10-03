package com.ahmedkhaled.bmicalculator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.CustomViewPager;
import com.ahmedkhaled.bmicalculator.MainActivity;
import com.ahmedkhaled.bmicalculator.R;

/**
 * Created by Ahmed Khaled on 9/13/2017.
 */

public class FragmentThree extends Fragment  {

    EditText Height,Weight;
    String height,weight;
    CustomViewPager viewPager;
    OnDataCollectedListener onDataCollectedListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onDataCollectedListener= (OnDataCollectedListener) getActivity();
        final View view=inflater.inflate(R.layout.fragment_three,container,false);
        Weight= (EditText) view.findViewById(R.id.Weight);
        Height = (EditText) view.findViewById(R.id.Height);
        viewPager=((MainActivity)getActivity()).viewPager;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_DRAGGING){
                    if (viewPager.getCurrentItem()==2) {
                        if (isDataCompleted()){
                            onDataCollectedListener.onSecondDataCollectedListener(weight, height);
                        }else {
                            viewPager.setEnabled(false);
                            Toast.makeText(getActivity(),"please enter weight & height ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        Weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                viewPager.setEnabled(true);
            }
        });
        Height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                viewPager.setEnabled(true);
            }
        });

        return view;
    }



    boolean isDataCompleted(){
        weight=Weight.getText().toString();
        height = Height.getText().toString();
        if (weight.length()>0&& height.length()>0){
            return true;
        }
        return false;
    }



    public interface OnDataCollectedListener {
        void onSecondDataCollectedListener(String weight,String height);

    }


}

package com.ahmedkhaled.bmicalculator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmedkhaled.bmicalculator.CustomViewPager;
import com.ahmedkhaled.bmicalculator.acivities.MainActivity;
import com.ahmedkhaled.bmicalculator.R;

/**
 * Created by Ahmed Khaled on 9/13/2017.
 */

public class FragmentThree extends Fragment  {

    EditText Height;
    String height;
    CustomViewPager viewPager;
    OnDataCollectedListener onDataCollectedListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onDataCollectedListener= (OnDataCollectedListener) getActivity();
        final View view=inflater.inflate(R.layout.fragment_three,container,false);
        Height = view.findViewById(R.id.Height);
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
                            onDataCollectedListener.onSecondDataCollectedListener(height);
                        }else {
                            viewPager.setEnabled(false);
                            Toast.makeText(getActivity(),"please enter weight & height ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        Height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewPager.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }



    boolean isDataCompleted(){
        height = Height.getText().toString();
        return height.length() > 0;
    }



    public interface OnDataCollectedListener {
        void onSecondDataCollectedListener(String height);

    }


}

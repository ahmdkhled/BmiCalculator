package com.ahmedkhaled.bmicalculator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class FragmentTwo extends Fragment  {
    EditText weight;
    String Weight;
    CustomViewPager viewPager;
    OnDataCollectedListener onDataCollectedListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        onDataCollectedListener= (OnDataCollectedListener) getActivity();
        final View view=inflater.inflate(R.layout.fragment_two,container,false);
        viewPager=((MainActivity)getActivity()).viewPager;
        weight=view.findViewById(R.id.weight);
        weight.addTextChangedListener(new TextWatcher() {
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==ViewPager.SCROLL_STATE_DRAGGING ){
                    if (viewPager.getCurrentItem()==1) {
                        if (isDataCompleted()){
                            viewPager.setEnabled(true);
                            Log.d("TAG","data is completed");
                            onDataCollectedListener.onDataCollectedListener(Weight);
                        }else {
                            Toast.makeText(getActivity(),"please enter weight ",Toast.LENGTH_LONG).show();
                            viewPager.setEnabled(false);
                        }
                    }
                }else {
                    viewPager.setEnabled(true);
                }
            }
        });

        return view;
    }

    boolean isDataCompleted(){
        Weight= weight.getText().toString();
        return Weight.length() > 0;
    }



    public interface OnDataCollectedListener {
        void onDataCollectedListener(String weight);
    }
}

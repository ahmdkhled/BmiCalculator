package com.ahmedkhaled.bmicalculator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ahmedkhaled.bmicalculator.fragments.FragmentOne;
import com.ahmedkhaled.bmicalculator.fragments.FragmentThree;
import com.ahmedkhaled.bmicalculator.fragments.FragmentTwo;

/**
 * Created by Ahmed Khaled on 9/13/2017.
 */

public class IntroPagerAdapter extends FragmentPagerAdapter {

    Fragment[] fragments=new Fragment[]{new FragmentOne(),new FragmentTwo(),new FragmentThree()};

    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}

package com.ahmedkhaled.bmicalculator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Ahmed Khaled on 9/24/2017.
 */

public class CustomViewPager extends ViewPager {

    boolean enabled;

    public CustomViewPager(Context context) {
        super(context);
        this.setEnabled(true);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (this.enabled) {
            return super.onTouchEvent(ev);
        }else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
         if (this.enabled) {
            return super.onInterceptTouchEvent(ev);
        }else {
             return false;
         }

    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

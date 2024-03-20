package com.trycatch_tanmay.naamkaran;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NonSwipeableViewPager extends ViewPager {
    private boolean isPagingEnabled = true;

    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    // Method to enable or disable swiping
    public void setPagingEnabled(boolean enabled) {
        this.isPagingEnabled = enabled;
    }
}

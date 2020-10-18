package com.nemo.demo.recycle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.nemo.demo.Constants;


public class RecycleTextView extends TextView {
    public RecycleTextView(Context context) {
        super(context);
    }

    public RecycleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(Constants.TAG, "RecycleTextView:onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(Constants.TAG, "RecycleTextView:onDetachedFromWindow");
    }


}

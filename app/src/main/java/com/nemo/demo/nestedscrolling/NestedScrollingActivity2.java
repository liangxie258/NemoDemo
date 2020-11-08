package com.nemo.demo.nestedscrolling;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.nemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedScrollingActivity2 extends ComponentActivity {

    @BindView(R.id.scrolllChildView)
    NestedScrollingChildView scrolllChildView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedscrolling2);
        ButterKnife.bind(this);
        scrolllChildView.setNestedScrollingEnabled(true);
    }


}
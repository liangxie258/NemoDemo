package com.nemo.demo.nestedscrolling;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.nemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedScrollingActivity extends Activity {

    @BindView(R.id.scrolllChildView)
    NestedScrollingChildView scrolllChildView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedscrolling);
        ButterKnife.bind(this);
        scrolllChildView.setNestedScrollingEnabled(true);
    }


}

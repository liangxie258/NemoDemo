package com.nemo.demo;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Demo中展示了CoordinatorLayout与AppBarLayout结合使用的方式
 */
public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_coordinator);
//        setContentView(R.layout.activity_coordinator_demo1);
//        setContentView(R.layout.activity_coordinator_demo2);
        setContentView(R.layout.activity_coordinator_demo3);
    }

}

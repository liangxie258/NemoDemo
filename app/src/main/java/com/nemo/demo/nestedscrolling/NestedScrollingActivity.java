package com.nemo.demo.nestedscrolling;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nemo.demo.R;
import com.nemo.demo.adapter.SimpleRecycleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedScrollingActivity extends Activity {

    @BindView(R.id.scrolllChildView)
    NestedScrollingChildView scrolllChildView;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedscrolling);
        ButterKnife.bind(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new SimpleRecycleAdapter());
//        scrolllChildView.setNestedScrollingEnabled(true);
    }


}

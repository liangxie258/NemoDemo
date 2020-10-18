package com.nemo.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nemo.demo.adapter.SimpleBaseAdapter;
import com.nemo.demo.adapter.SimpleRecycleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在ScrollView中ListView的高度只能显示一行
 * 但是在ScrollView中RecycleView将占据所能占据的最大高度
 */
public class ScrollViewActivity extends Activity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        ButterKnife.bind(this);
        rvList.setAdapter(new SimpleRecycleAdapter());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);
        //插入值
        lvList.setAdapter(new SimpleBaseAdapter(getApplicationContext()));
    }
}

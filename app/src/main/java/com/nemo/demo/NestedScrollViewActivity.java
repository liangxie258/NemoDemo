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
 * NestedScrollView嵌套ListView后，ListView只能显示一行，并且，ListView和NestedScrollView出现
 * 滑动冲突，导致，ListView不能滑动
 * NestedScrollView嵌套RecycleView，由于RecycleView会尽可能的充满自身，导致Recycle加载全部的itemView
 * ，RecycleView的复用机制失效。
 */
public class NestedScrollViewActivity extends Activity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.rv_list2)
    RecyclerView rvList2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedscrollview);
        ButterKnife.bind(this);
        rvList.setAdapter(new SimpleRecycleAdapter());
        rvList2.setAdapter(new SimpleRecycleAdapter());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        rvList2.setLayoutManager(linearLayoutManager2);
        //插入值
        lvList.setAdapter(new SimpleBaseAdapter(getApplicationContext()));
    }
}

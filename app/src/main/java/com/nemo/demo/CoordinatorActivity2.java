package com.nemo.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.nemo.demo.adapter.SimpleRecycleAdapter;
import com.nemo.demo.adapter.SimpleRecycleAdapter2;
import com.nemo.demo.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.core.view.ViewCompat.TYPE_NON_TOUCH;

public class CoordinatorActivity2 extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rv_list_landscape)
    RecyclerView rvListLandscape;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.title2)
    TextView title2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_coordinator);
//        setContentView(R.layout.activity_coordinator_demo1);
//        setContentView(R.layout.activity_coordinator_demo2);
        setContentView(R.layout.activity_coordinator_demo4);
        ButterKnife.bind(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new SimpleRecycleAdapter());
        rvListLandscape.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListLandscape.setAdapter(new SimpleRecycleAdapter2());
        title2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        appbarLayout.setExpanded(false);

//        //置顶
//        CoordinatorLayout.Behavior behavior1 = ((CoordinatorLayout.LayoutParams) appbarLayout.getLayoutParams()).getBehavior();
//        if (behavior1 != null) {
//            behavior1.onNestedPreScroll(coordinator, appbarLayout, rvList, 0, DensityUtil.dp2px(rvList.getTop()), new int[]{0, 0}, TYPE_NON_TOUCH);
//        }

    }
}

package com.nemo.demo.basequickadpter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nemo.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseQuickActivity extends AppCompatActivity {

    private static List<String> datas = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++)
            datas.add("" + i);
    }

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_quick);
        ButterKnife.bind(this);
        SimpleQuickAdapter simpleQuickAdapter=new SimpleQuickAdapter(android.R.layout.simple_list_item_1,datas);
        rvList.setLayoutManager(new LinearLayoutManager(getApplication()));
        rvList.setAdapter(simpleQuickAdapter);
//        simpleQuickAdapter.getViewByPosition()
        

    }
}

package com.nemo.demo.scroll;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollActivity extends AppCompatActivity {

    @BindView(R.id.btn_scroll)
    Button btnScroll;
    @BindView(R.id.tv_srcoll)
    TestView2 tvSrcoll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_scroll)
    public void onViewClicked() {
//        tvSrcoll.scrollBy(-10, -10);
//        tvSrcoll.smoothScrollBy(-50,-50);


    }
}

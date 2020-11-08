package com.nemo.demo.basequickadpter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SimpleQuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SimpleQuickAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1,item);
//        helper.getItemViewType()
    }
}

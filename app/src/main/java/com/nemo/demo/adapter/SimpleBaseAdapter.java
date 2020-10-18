package com.nemo.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nemo.demo.Constants;
import com.nemo.demo.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleBaseAdapter extends ArrayAdapter<String> {

    private static List<String> datas = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++)
            datas.add("" + i);
    }

    public SimpleBaseAdapter(@NonNull Context context) {
        this(context, android.R.layout.simple_list_item_1,datas);
    }

    public SimpleBaseAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.i(Constants.TAG, "getView      position : " + position);
        return super.getView(position, convertView, parent);
    }
}

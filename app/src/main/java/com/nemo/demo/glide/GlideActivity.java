package com.nemo.demo.glide;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;

    String path="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604599256821&di=d72b9a18142a414baa445d9242c4622d&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F70%2F91%2F01300000261284122542917592865.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
//        RequestOptions options = new RequestOptions();
//        options.
        Glide.with(this).load(path).error(new ColorDrawable(Color.RED)).transform(new CircleCrop()).into(image);

    }
}

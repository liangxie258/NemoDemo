package com.nemo.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutoSizeActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_size);
        ButterKnife.bind(this);
        DisplayMetrics displayMetrics = MyApplication.application.getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        float density = displayMetrics.density;
        float scaledDensity = displayMetrics.scaledDensity;
        Log.i(Constants.TAG, "AutoSizeActivity---------------" +
                "\nwidthPixels:" + widthPixels + "   \nheightPixels" + heightPixels + "     \ndensity" + density + "    \nscaledDensity" + scaledDensity);
//        TypedValue.applyDimension()

    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        tv.setWidth(dip2px(this,360));
        tv.setHeight(dip2px(this,200));
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.i(Constants.TAG, "dxValue:" + (dpValue * scale + 0.5f));
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

package com.nemo.demo.animator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorActivity extends AppCompatActivity {

    @BindView(R.id.move_image)
    MoveImageView moveImage;

    String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604599256821&di=d72b9a18142a414baa445d9242c4622d&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F70%2F91%2F01300000261284122542917592865.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);
        Glide.with(this).load(path).into(moveImage);
    }

    boolean isFirst = true;

    @OnClick(R.id.move_image)
    public void onViewClicked() {
        /**
         * 重要：
         * 属性动画并不会造成控件onLayout和onDraw方法的触发，也就是不会造成重绘
         */
//        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(moveImage, "translationX", 0, 100);
//        objectAnimator.setDuration(2000);
//        objectAnimator.start();

//        ObjectAnimator scaleXobjectAnimator=ObjectAnimator.ofFloat(moveImage, "scaleX", 0f, 1.2f);
//        scaleXobjectAnimator.setDuration(2000);
//        scaleXobjectAnimator.start();

//        if (isFirst) {
//            moveImage.setTranslationX(50);
//            moveImage.setTranslationY(50);
//            isFirst = false;
//        } else {
//            moveImage.setTranslationX(200);
//            moveImage.setTranslationY(200);
//        }
//        handler.post(runnable);
    }

    int TranslationX;
    int count;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            TranslationX += 5;
            if (count < 50) {
                moveImage.setTranslationY(TranslationX);
                count++;
                handler.post(this);
            }
        }
    };

    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);

        }
    };
}

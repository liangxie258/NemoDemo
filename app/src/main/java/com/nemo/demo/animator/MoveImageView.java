package com.nemo.demo.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class MoveImageView extends ImageView {

    private String TAG = MoveImageView.class.getSimpleName();

    public MoveImageView(Context context) {
        super(context);
    }

    public MoveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "MoveImageView:onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "MoveImageView:onDraw");
        parentWidth = ((ViewGroup) getParent()).getMeasuredWidth();
        parentHeight = ((ViewGroup) getParent()).getMeasuredHeight();
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    float lastX;
    float lastY;
    int parentWidth;
    int parentHeight;
    int width;
    int height;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        MotionEvent eventCopy = MotionEvent.obtain(event);
//        eventCopy.offsetLocation(0, 0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
//                lastX = eventCopy.getX();
//                lastY = eventCopy.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX();
                float moveY = event.getRawY();
//                float moveX = eventCopy.getX();
//                float moveY = eventCopy.getY();
                float distanceX = moveX - lastX;
                float distanceY = moveY - lastY;
                float marginX = getX();//取得空间距离父控件左侧的距离
                float marginY = getY();//取得空间距离父控件左侧的距离
                float needX = 0;
                float needY = 0;
                if (marginX > 0 || marginX + width < parentWidth) {
                    if (marginX + distanceX < 0) {
                        marginX = 0;
                    } else if (marginX + width + distanceX > parentWidth) {
                        marginX = parentWidth - width;
                    } else {
                        marginX += distanceX;
                    }
                    needX = marginX - getLeft();
                }
                Log.i("MoveImageView", "onTouchEvent:   lastX---" + lastX + "\n   distanceX---" + distanceX + "\n   needX---" + needX + "\n   marginX---" + marginX);
                if (marginY > 0 || marginY + height < parentHeight) {
                    if (marginY + distanceY < 0) {
                        marginY = 0;
                    } else if (marginY + height + distanceY > parentHeight) {
                        marginY = parentHeight - height;
                    } else {
                        marginY += distanceY;
                    }
                    needY = marginY - getTop();
                }
                setTranslationX(needX);
                setTranslationY(needY);
                lastX = moveX;
                lastY = moveY;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
//        return true;
        return super.onTouchEvent(event);
    }
}

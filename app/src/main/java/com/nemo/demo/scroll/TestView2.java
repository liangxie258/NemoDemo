package com.nemo.demo.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class TestView2 extends View {

    Paint mPaint;
    //    Scroller scroller;
    int mSlop;

    public TestView2(Context context) {
        super(context);
    }

    public TestView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mSlop = ViewConfiguration.getTouchSlop();
//        scroller = new Scroller(context);
    }

    public TestView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawCircle(0, 0, 40.0f, mPaint);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    int lastX;
    int lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();
                int differenceX = currentX - lastX;
                int differenceY = currentY - lastY;
                if (Math.abs(differenceX) > mSlop || Math.abs(differenceY) > mSlop) {
                    scrollBy(-differenceX,-differenceY);
                    lastX = (int) event.getX();
                    lastY = (int) event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}

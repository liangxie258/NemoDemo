package com.nemo.demo.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class TestView extends View {

    Paint mPaint;
    Scroller scroller;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        scroller = new Scroller(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void smoothScrollBy(int dx, int dy) {
        scroller.forceFinished(true);
        int startX = getScrollX();
        int startY = getScrollY();
        int targetX = startX + dx;
        int targetY = startY + dy;
        Log.i("scroll", "startX:" + startX + "   startY:" + startY +"     targetX:" + targetX + "   targetY:" + targetY);
        scroller.startScroll(startX, startY, targetX, targetY, 2000);
        invalidate();
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
        if (scroller.computeScrollOffset()) {
            int targetX = scroller.getCurrX();
            int targetY = scroller.getCurrY();
            Log.i("scroll", "targetX:" + targetX + "   targetY:" + targetY);
            scrollTo(targetX, targetY);
            postInvalidate();
        }
    }
}

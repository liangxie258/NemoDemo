package com.nemo.demo.nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;

import com.nemo.demo.Constants;

import java.util.Arrays;

public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {

    public StickyNavLayout(Context context) {
        super(context);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 决定是否接收子View的滚动事件
     *
     * @param nestedScrollAxes 滑动的轴线
     *                         纵向滑动：nestedScrollAxes=ViewCompat.SCROLL_AXIS_VERTICAL;
     *                         横向滑动：nestedScrollAxes=ViewCompat.SCROLL_AXIS_HORIZONTAL;
     *                         无滑动：nestedScrollAxes=ViewCompat.SCROLL_AXIS_NONE;
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i(Constants.TAG, "onStartNestedScroll:   child:" + child + "   target:" + target + "    nestedScrollAxes:" + nestedScrollAxes);
        return true;
//        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    // 响应子View的滚动
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.i(Constants.TAG, "onNestedScrollAccepted:   child:" + child + "   target:" + target + "    axes:" + axes);
        super.onNestedScrollAccepted(child, target, axes);
    }

    // 滚动结束的回调
    @Override
    public void onStopNestedScroll(View child) {
        Log.i(Constants.TAG, "onStopNestedScroll:   child:" + child);
        super.onStopNestedScroll(child);
    }

    // ns child滚动后回调
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(Constants.TAG, "onNestedScroll:   target:" + target + "   dxConsumed:" + dxConsumed + "    dyConsumed:" + dyConsumed + "    dxUnconsumed:" + dxUnconsumed + "     dyUnconsumed:" + dyUnconsumed);
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    // ns child滚动前回调
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.i(Constants.TAG, "onNestedPreScroll:   target:" + target + "   dx:" + dx + "    dy:" + dy + "    consumed:" + Arrays.toString(consumed));
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    // ns child滚动后回调
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(Constants.TAG, "onNestedFling:   target:" + target + "   velocityX:" + velocityX + "    velocityY:" + velocityY + "    consumed:" + consumed);
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    // ns child flying前回调
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i(Constants.TAG, "onNestedPreFling:   target:" + target + "   velocityX:" + velocityX + "    velocityY:" + velocityY);
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    // 返回当前布局嵌套滚动的坐标轴
    @Override
    public int getNestedScrollAxes() {
        Log.i(Constants.TAG, "getNestedScrollAxes");
        return super.getNestedScrollAxes();
    }
}

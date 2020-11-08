package com.nemo.demo.nestedscrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;

import com.nemo.demo.Constants;

public class NestedScrollingChildView extends TextView implements NestedScrollingChild {
    public NestedScrollingChildView(Context context) {
        super(context);
    }

    public NestedScrollingChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (mChildHelper == null) {
            mChildHelper = new NestedScrollingChildHelper(this);
        }
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        Log.i(Constants.TAG, "setNestedScrollingEnabled------   enabled:" + enabled);
        getNestedScrollingHelper().setNestedScrollingEnabled(enabled);
//        super.setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        Log.i(Constants.TAG, "isNestedScrollingEnabled------");
        return getNestedScrollingHelper().isNestedScrollingEnabled();
//        return super.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(@ViewCompat.ScrollAxis int axes) {
        Log.i(Constants.TAG, "startNestedScroll------    axes:" + axes);
        return getNestedScrollingHelper().startNestedScroll(axes);
//        return getNestedScrollingHelper().startNestedScroll(axes);
//        return super.startNestedScroll(axes);
    }

    public void stopNestedScroll() {
        Log.i(Constants.TAG, "stopNestedScroll------");
        getNestedScrollingHelper().stopNestedScroll();
//        getNestedScrollingHelper().stopNestedScroll();
//        super.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        Log.i(Constants.TAG, "hasNestedScrollingParent------");
        return getNestedScrollingHelper().hasNestedScrollingParent();
//        return getNestedScrollingHelper().hasNestedScrollingParent();
//        return super.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                        int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        Log.i(Constants.TAG, "dispatchNestedScroll------    dxConsumed:" + dxConsumed + "    dyConsumed:" + dyConsumed + "    dxUnconsumed:" + dxUnconsumed + "    dyUnconsumed:" + dyUnconsumed + "    offsetInWindow:" + offsetInWindow);
        return getNestedScrollingHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
//        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed,
                                           @Nullable int[] offsetInWindow) {
        Log.i(Constants.TAG, "dispatchNestedPreScroll------    dx:" + dx + "    dy:" + dy + "    consumed:" + consumed + "    offsetInWindow:" + offsetInWindow);
        return getNestedScrollingHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
//        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.i(Constants.TAG, "dispatchNestedFling------    velocityX:" + velocityX + "    velocityY:" + velocityY + "    consumed:" + consumed);
        return getNestedScrollingHelper().dispatchNestedFling(velocityX, velocityY, consumed);
//        return super.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.i(Constants.TAG, "dispatchNestedPreFling------    velocityX:" + velocityX + "    velocityY:" + velocityY);
        return getNestedScrollingHelper().dispatchNestedPreFling(velocityX, velocityY);
//        return super.dispatchNestedPreFling(velocityX, velocityY);
    }

    private NestedScrollingChildHelper getNestedScrollingHelper() {
        if (mChildHelper == null) {
            mChildHelper = new NestedScrollingChildHelper(this);
        }
        return mChildHelper;
    }

    private NestedScrollingChildHelper mChildHelper;

    int mLastY;
    int mFirstY;
    boolean mIsSelfFling;
    boolean mHasFling;
    int[] mScrollConsumed = new int[2];

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) event.getRawY();
                mFirstY = mLastY;
                initOrResetVelocityTracker();
                mIsSelfFling = false;
                mHasFling = false;
                boolean needScroll = startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                Log.i(Constants.TAG, "needScroll:" + needScroll);
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                int y = (int) event.getRawY();
                int dy = y - mLastY;
                mLastY = y;
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
//                if (dy < 0) {
                dispatchNestedPreScroll(0, -dy, mScrollConsumed, null);
//                } else {
//                    dispatchNestedScroll(0, dy, 0, 0, null);
//                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());
                int yVelocity = (int) -mVelocityTracker.getYVelocity();
                dispatchNestedPreFling(0, yVelocity);
                recycleVelocityTracker();
                break;
        }
        super.onTouchEvent(event);
        return true;
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
        }
        mVelocityTracker = null;
    }

    VelocityTracker mVelocityTracker;

    //初始化速度跟踪器
    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }


//    @Override
//    public boolean startNestedScroll(int axes, int type) {
//        return false;
//    }
//
//    @Override
//    public void stopNestedScroll(int type) {
//
//    }
//
//    @Override
//    public boolean hasNestedScrollingParent(int type) {
//        return false;
//    }
//
//    @Override
//    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
//        return false;
//    }
//
//    @Override
//    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
//        return false;
//    }
}

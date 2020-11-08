package com.nemo.demo.recycle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nemo.demo.Constants;
import com.nemo.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecycleActivity extends AppCompatActivity {

    private static final String TAG = RecycleActivity.class.getSimpleName();

    @BindView(R.id.rv_main_list)
    RecyclerView rvMainList;
    @BindView(R.id.add)
    TextView add;

    DataAdapter dataAdapter;

    private char[] letters = new char[26];

    private List<String> datas;

    @OnClick(R.id.add)
    public void onViewClicked() {
//        datas.remove(1);
//        dataAdapter.notifyItemRemoved(1);

        Animation animation = new TranslateAnimation(0, 50, 0, 50);
        animation.setDuration(5000);
        View view0 = rvMainList.getLayoutManager().getChildAt(0);
        View view1 = rvMainList.getLayoutManager().getChildAt(1);
        View view2 = rvMainList.getLayoutManager().getChildAt(2);
        view0.startAnimation(animation);
        view1.startAnimation(animation);
        view2.startAnimation(animation);
    }

    public interface ItemInitial {
        char getItemInitial(int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMainList.setLayoutManager(linearLayoutManager);
//        rvMainList.addItemDecoration(new Divider());
//        rvMainList.addItemDecoration(new LeftTag());
//        rvMainList.addItemDecoration(new Header());
//        rvMainList.addItemDecoration(new StickHeader());
        initData();
        dataAdapter = new DataAdapter();
        rvMainList.setAdapter(dataAdapter);
//        rvMainList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        datas = new ArrayList<>();
        for (byte i = 0; i < 26; i++) {
            letters[i] = (char) ('A' + i);
            datas.add(String.valueOf(letters[i]));
        }
        Log.i(TAG, Arrays.toString(letters));
    }

    class DataAdapter extends RecyclerView.Adapter implements ItemInitial {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_recycle, parent, false);
            return new DataViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            DataViewHolder dataViewHolder = (DataViewHolder) holder;
//            dataViewHolder.tvRecycleContent.setText(letters[position] + "");
            dataViewHolder.tvRecycleContent.setText(datas.get(position) + "");
        }

        @Override
        public int getItemCount() {
            return datas.size();
//            return letters.length;
        }

        @Override
        public char getItemInitial(int position) {
            char cccc = datas.get(position).toCharArray()[0];
            return cccc;
//            return letters[position];
        }

        class DataViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_recycle_content)
            TextView tvRecycleContent;

            public DataViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    class Divider extends RecyclerView.ItemDecoration {

        Paint paint;

        public Divider() {
            paint = new Paint();
            paint.setColor(Color.RED);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = 10;
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = parent.getChildAt(i);
                int bottom = childView.getBottom();
                int right = childView.getRight();
                c.drawRect(new Rect(0, bottom, right, bottom + 10), paint);
            }
        }
    }

    class LeftTag extends RecyclerView.ItemDecoration {

        Paint paint;

        public LeftTag() {
            paint = new Paint();
            paint.setColor(Color.BLUE);
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = parent.getChildAt(i);
                int top = childView.getTop();
                int bottom = childView.getBottom();
                c.drawRect(new Rect(0, top, 40, bottom), paint);
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = 40;
        }
    }

    class Header extends RecyclerView.ItemDecoration {

        Paint paint;
        Paint textpaint;

        public Header() {
            paint = new Paint();
            textpaint = new Paint();
            paint.setColor(Color.parseColor("#B4EEB4"));
            textpaint.setTextSize(22);
            textpaint.setColor(Color.BLACK);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = 50;
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = parent.getChildAt(i);
                int parentWidth = parent.getWidth();
                ItemInitial itemInitial = (ItemInitial) parent.getAdapter();
                int childAdapterPosition = parent.getChildAdapterPosition(childView);
                char itemChar = itemInitial.getItemInitial(childAdapterPosition);
                if (childAdapterPosition == 0) {
                    int top = childView.getTop();
                    int right = childView.getRight();
                    c.drawRect(new Rect(0, top - 50, right, top), paint);
                    // 文字宽
                    float textWidth = paint.measureText(itemChar + "");
                    // 文字baseline在y轴方向的位置
                    float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                    c.drawText(itemChar + "", (parentWidth - textWidth) / 2, top - 50 / 2 + baseLineY, textpaint);
                } else {
                    char previousItemChar = itemInitial.getItemInitial(childAdapterPosition - 1);
                    if (itemChar != previousItemChar) {//如果上一个item的标记和本条item的标记不相同，则需要显示HEAR
                        int top = childView.getTop();
                        int right = childView.getRight();
                        c.drawRect(new Rect(0, top - 50, right, top), paint);
//                        c.drawText(itemChar+"", parentWidth/2,top - 50/2,textpaint);
                        // 文字宽
                        float textWidth = paint.measureText(itemChar + "");
                        // 文字baseline在y轴方向的位置
                        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                        c.drawText(itemChar + "", (parentWidth - textWidth) / 2, top - 50 / 2 + baseLineY, textpaint);
                    }
                }
            }
        }
    }

    class StickHeader extends RecyclerView.ItemDecoration {

        Paint paint;
        Paint textpaint;

        public StickHeader() {
            paint = new Paint();
            textpaint = new Paint();
            paint.setColor(Color.parseColor("#B4EEB4"));
            textpaint.setTextSize(22);
            textpaint.setColor(Color.BLACK);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = 50;
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = parent.getChildAt(i);
                int parentWidth = parent.getWidth();
                ItemInitial itemInitial = (ItemInitial) parent.getAdapter();
                int childAdapterPosition = parent.getChildAdapterPosition(childView);
                char itemChar = itemInitial.getItemInitial(childAdapterPosition);
                if (childAdapterPosition == 0) {
                    int top = childView.getTop();
                    int right = childView.getRight();
                    c.drawRect(new Rect(0, top - 50, right, top), paint);
                    // 文字宽
                    float textWidth = paint.measureText(itemChar + "");
                    // 文字baseline在y轴方向的位置
                    float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                    c.drawText(itemChar + "", (parentWidth - textWidth) / 2, top - 50 / 2 + baseLineY, textpaint);
                } else {
                    char previousItemChar = itemInitial.getItemInitial(childAdapterPosition - 1);
                    if (itemChar != previousItemChar) {//如果上一个item的标记和本条item的标记不相同，则需要显示HEAR
                        int top = childView.getTop();
                        int right = childView.getRight();
                        c.drawRect(new Rect(0, top - 50, right, top), paint);
//                        c.drawText(itemChar+"", parentWidth/2,top - 50/2,textpaint);
                        // 文字宽
                        float textWidth = paint.measureText(itemChar + "");
                        // 文字baseline在y轴方向的位置
                        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                        c.drawText(itemChar + "", (parentWidth - textWidth) / 2, top - 50 / 2 + baseLineY, textpaint);
                    }
                }
            }
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            int parentWidth = parent.getWidth();
            View firstView = parent.getChildAt(0);
            int firstAdapterPosition = parent.getChildAdapterPosition(firstView);
            if (firstAdapterPosition + 1 < parent.getAdapter().getItemCount()) {//如果还存在下一个View
                ItemInitial itemInitial = (ItemInitial) parent.getAdapter();
                char firstInitial = itemInitial.getItemInitial(firstAdapterPosition);
                char nextInitial = itemInitial.getItemInitial(firstAdapterPosition + 1);
                if (firstInitial == nextInitial) {//如果第一个显示item和下一个item的首字母相同则属于同一组，不需要对header进行判断
                    // 文字宽
                    float textWidth = paint.measureText(firstInitial + "");
                    // 文字baseline在y轴方向的位置
                    float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                    c.drawRect(new Rect(0, 0, parentWidth, 50), paint);
                    c.drawText(firstInitial + "", (parentWidth - textWidth) / 2, 50 / 2 + baseLineY, textpaint);
                } else {
                    int bottom = firstView.getBottom();
                    int top = firstView.getTop();
                    if (bottom < 50) {
                        // 文字宽
                        float textWidth = paint.measureText(firstInitial + "");
                        // 文字baseline在y轴方向的位置
                        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                        c.drawRect(new Rect(0, bottom - 50, parentWidth, bottom), paint);
                        c.drawText(firstInitial + "", (parentWidth - textWidth) / 2, bottom - (25 - baseLineY), textpaint);
                    } else {
                        // 文字宽
                        float textWidth = paint.measureText(firstInitial + "");
                        // 文字baseline在y轴方向的位置
                        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                        c.drawRect(new Rect(0, 0, parentWidth, 50), paint);
                        c.drawText(firstInitial + "", (parentWidth - textWidth) / 2, 50 / 2 + baseLineY, textpaint);
                    }
                }
            } else {
                ItemInitial itemInitial = (ItemInitial) parent.getAdapter();
                char firstInitial = itemInitial.getItemInitial(firstAdapterPosition);
                char nextInitial = itemInitial.getItemInitial(firstAdapterPosition + 1);
                // 文字宽
                float textWidth = paint.measureText(firstInitial + "");
                // 文字baseline在y轴方向的位置
                float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
                c.drawRect(new Rect(0, 0, parentWidth, 50), paint);
                c.drawText(firstInitial + "", (parentWidth - textWidth) / 2, 50 / 2 + baseLineY, textpaint);
            }

        }
    }
}


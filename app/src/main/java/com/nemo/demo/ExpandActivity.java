package com.nemo.demo;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandActivity extends AppCompatActivity {


    @BindView(R.id.expand_list)
    ExpandableListView expandList;

    private String[] groups = {"开发部", "人力资源部", "销售部"};

    private String[][] childs = {{"赵珊珊", "钱丹丹", "孙可可", "李冬冬", "钱丹丹", "孙可可", "李冬冬", "钱丹丹", "孙可可", "李冬冬", "钱丹丹", "孙可可", "李冬冬"}
    , {"周大福", "吴端口", "郑非", "王疯狂", "吴端口", "郑非", "王疯狂", "吴端口", "郑非", "王疯狂", "吴端口", "郑非", "王疯狂"},
            {"冯程程", "陈类", "楚哦", "魏王", "陈类", "楚哦", "魏王", "陈类", "楚哦", "魏王", "陈类", "楚哦", "魏王", "陈类", "楚哦", "魏王"}};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ButterKnife.bind(this);
        ExpandableListviewAdapter adapter = new ExpandableListviewAdapter(this, groups, childs);
        expandList.setAdapter(adapter);
        expandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                showToastShort(groups[groupPosition]);
                return false;
            }
        });
        //子视图的点击事件
        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                showToastShort(childs[groupPosition][childPosition]);
                return true;
            }
        });
        //用于当组项折叠时的通知。
        expandList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                showToastShort("折叠了数据___" + groups[groupPosition]);
            }
        });
        //
        //用于当组项折叠时的通知。
        expandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                showToastShort("展开了数据___" + groups[groupPosition]);
            }
        });

    }

    private void showToastShort(String group) {
        Toast.makeText(this, group, Toast.LENGTH_SHORT).show();
    }

}


class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private String[] groups;
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs;
    private Context context;

    public ExpandableListviewAdapter(Context context, String[] groups, String[][] childs) {
        this.context = context;
        this.groups = groups;
        this.childs = childs;
    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return childs[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return groups[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return childs[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }

    @Override
/**
 *
 * 获取显示指定组的视图对象
 *
 * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态，true=展开
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
 */
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_parent_item, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.parent_textview_id = convertView.findViewById(R.id.parent_textview_id);
            groupViewHolder.parent_image = convertView.findViewById(R.id.parent_image);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.parent_textview_id.setText(groups[groupPosition]);
        //如果是展开状态，
        if (isExpanded) {
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.img_arrow_down));
        } else {
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.img_arrow_right));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_chidren_item, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.chidren_item = (TextView) convertView.findViewById(R.id.chidren_item);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.chidren_item.setText(childs[groupPosition][childPosition]);
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class GroupViewHolder {
        TextView parent_textview_id;
        ImageView parent_image;
    }

    static class ChildViewHolder {
        TextView chidren_item;

    }
}

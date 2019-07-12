package com.yeqiu.hailhaydra.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;

import java.util.ArrayList;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/22
 * @describe：
 * @fix：
 */
public class TitlebarGradientActivity extends BaseActivity {

    private ListView list;
    private LinearLayout head;

    // 滑动到什么地方开始变色
    private int height = 200;

    @Override
    protected Object getContentView() {
        return R.layout.activity_titlebargradient;
    }

    @Override
    protected void initView() {

        head = (LinearLayout) findViewById(R.id.ll_gradient_head);
        list = (ListView) findViewById(R.id.lv_gradient_list);
    }

    @Override
    protected void initData() {

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("Test" + i);
        }

        list.setCacheColorHint(Color.TRANSPARENT);
        list.setFadingEdgeLength(0);
        list.setAdapter(new SimpleAdapter(this, datas));
    }

    @Override
    public void initListener() {
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //计算滑动距离
                int scrollY = getScrollY(list);


                if (scrollY <= 0) {
                    //当前没有滑动，head全透明
                    head.setAlpha(0f);
                } else if (scrollY > 0 && scrollY <= height) {
                    //在滑动高度中时，设置透明度百分比（当前高度/总高度）
                    float d = (float) scrollY / height;
                    head.setAlpha(d);
                } else {
                    //滑出总高度 完全不透明
                    head.setAlpha(1f);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public int getScrollY(ListView listview) {
        View view = listview.getChildAt(0);
        if (view == null) {
            return 0;
        }
        int firstVisiblePosition = listview.getFirstVisiblePosition();
        int top = view.getTop();
        return -top + firstVisiblePosition * view.getHeight();
    }


    public class SimpleAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<String> datas;

        private DisplayMetrics mDisplayMetrics;

        public SimpleAdapter(Context context, ArrayList<String> datas) {
            this.mContext = context;
            this.datas = datas;
            mDisplayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int padding = (int) (mDisplayMetrics.density * 10);

            TextView tv = new TextView(mContext);
            tv.setText(datas.get(position));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tv.setTextColor(Color.parseColor("#468ED0"));

            tv.setPadding(padding, padding, padding, padding);
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams
                    .MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            return tv;
        }
    }

}

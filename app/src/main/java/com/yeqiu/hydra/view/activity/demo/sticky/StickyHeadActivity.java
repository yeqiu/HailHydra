package com.yeqiu.hydra.view.activity.demo.sticky;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/15
 * @describe：简单实现悬浮 详细 https://github.com/wobiancao/StickyDemo
 * @fix：
 */
public class StickyHeadActivity extends BaseActivity {

    private RecyclerView rvSticky;
    private TextView tvSticky;
    private TextView tvHeadSticky;
    private int tvY = 0;
    private View headView;

    @Override
    protected Object getContentView() {
        return R.layout.activity_sticky;
    }

    @Override
    protected void initView() {

        setHeaderTitle("简单实现列表悬浮");

        rvSticky = (RecyclerView) findViewById(R.id.rv_sticky);
        tvSticky = (TextView) findViewById(R.id.tv_sticky);

        rvSticky.setLayoutManager(new LinearLayoutManager(this));

        headView = View.inflate(getContext(), R.layout.layout_head_sticky_header, null);
        tvHeadSticky = (TextView) headView.findViewById(R.id.tv_sticky);


    }

    @Override
    protected void initData() {


        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("StickyHead" + i);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(R.layout.item_sticky, datas);
        rvSticky.setAdapter(simpleAdapter);

        simpleAdapter.addHeaderView(headView);

    }

    @Override
    protected void initListener() {


        rvSticky.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //获取布局里悬浮控件距离Y轴的距离
                tvY = getDistanceY(tvSticky);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (tvHeadSticky == null) {
                    return;
                }
                //获取头布局里悬浮控件距离Y轴的距离
                int getTop = getDistanceY(tvHeadSticky);
                if (getTop <= tvY) {
                    tvSticky.setVisibility(View.VISIBLE);
                } else {
                    tvSticky.setY(0);
                    tvSticky.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }


    /**
     * 量取view此时Y轴的距离
     *
     * @return
     */
    public int getDistanceY(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int y = location[1];
        return y;
    }


    class SimpleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SimpleAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

            helper.setText(R.id.tv_item_sticky, item);
        }
    }
}

package com.yeqiu.hydra.view.activity.demo;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/16
 * @describe：
 * @fix：
 */
public class TitlebarGradientActivity extends BaseActivity {

    private RecyclerView rvTitlebarGradient;
    private RelativeLayout rlTitlebarGradientHead;
    /**
     * 滑动到什么地方开始变色
     */
    private int height = 200;

    @Override
    protected Object getContentView() {
        return R.layout.activity_titlebar_gradient;
    }

    @Override
    protected void initView() {

        showHeadBar(false);
        rvTitlebarGradient = (RecyclerView) findViewById(R.id.rv_titlebar_gradient);
        rlTitlebarGradientHead = (RelativeLayout) findViewById(R.id.rl_titlebar_gradient_head);
        rvTitlebarGradient.setLayoutManager(new LinearLayoutManager(getActivity()));

        rlTitlebarGradientHead.setAlpha(0f);
    }

    @Override
    protected void initData() {

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("CoordinatorLayout实现悬浮" + i);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(R.layout.item_sticky, datas);
        rvTitlebarGradient.setAdapter(simpleAdapter);
    }

    @Override
    protected void initListener() {


        rvTitlebarGradient.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollY = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                scrollY = scrollY + dy;

                if (scrollY <= 0) {
                    //没有滑动 head透明
                    rlTitlebarGradientHead.setAlpha(0f);
                } else if (scrollY > 0 && scrollY <= height) {
                    //滑动中，设置透明度
                    float alpha = (float)scrollY / height;
                    rlTitlebarGradientHead.setAlpha(alpha);
                } else {
                    rlTitlebarGradientHead.setAlpha(1f);
                }


            }
        });


    }


    @Override
    public void onClick(View v) {

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

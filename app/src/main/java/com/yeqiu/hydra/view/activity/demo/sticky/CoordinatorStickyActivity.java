package com.yeqiu.hydra.view.activity.demo.sticky;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

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
public class CoordinatorStickyActivity extends BaseActivity {

   private RecyclerView rvCoordinatorSticky;

    @Override
    protected Object getContentView() {
        return R.layout.activity_coordinator_sticky;
    }

    @Override
    protected void initView() {
        setHeaderTitle("CoordinatorLayout实现悬浮");
        rvCoordinatorSticky = (RecyclerView) findViewById(R.id.rv_coordinator_sticky);
        rvCoordinatorSticky.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("CoordinatorLayout实现悬浮" + i);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(R.layout.item_sticky, datas);
        rvCoordinatorSticky.setAdapter(simpleAdapter);


    }

    @Override
    protected void initListener() {

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

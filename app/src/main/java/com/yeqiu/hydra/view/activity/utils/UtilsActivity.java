package com.yeqiu.hydra.view.activity.utils;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.bean.Model.ModelHaydraItem;
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
public class UtilsActivity extends BaseActivity {

    private RecyclerView rvDemo;

    @Override
    protected Object getContentView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView() {
        setHeaderTitle("工具类");
        rvDemo = (RecyclerView) findViewById(R.id.rv_demo);
        rvDemo.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {

        final List<ModelHaydraItem> datas = new ArrayList<>();
        datas.add(new ModelHaydraItem("StringUtils的使用", StringUtilsActivity.class));
        datas.add(new ModelHaydraItem("键盘开启关闭的监听", KeyBordUtilsActivity.class));
        datas.add(new ModelHaydraItem("TextTool的使用", TextToolActivity.class));
        datas.add(new ModelHaydraItem("ImageUtils的使用", ImageUtilsActivity.class));



       DemoAdapter demoAdapter = new DemoAdapter(R.layout.item_sticky,
               datas);
        rvDemo.setAdapter(demoAdapter);

        demoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(), datas.get(position).getActivity()));
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }


    class DemoAdapter extends BaseQuickAdapter<ModelHaydraItem, BaseViewHolder> {

        public DemoAdapter(int layoutResId, @Nullable List<ModelHaydraItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ModelHaydraItem item) {
            helper.setText(R.id.tv_item_sticky, item.getName());
        }
    }

}


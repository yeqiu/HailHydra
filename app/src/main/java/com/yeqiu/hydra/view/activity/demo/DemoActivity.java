package com.yeqiu.hydra.view.activity.demo;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.bean.model.ModelHaydraItem;
import com.yeqiu.hydra.view.activity.BaseActivity;
import com.yeqiu.hydra.view.activity.demo.sticky.CoordinatorStickyActivity;
import com.yeqiu.hydra.view.activity.demo.sticky.StickyHeadActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/16
 * @describe：
 * @fix：
 */
public class DemoActivity extends BaseActivity {

    private RecyclerView rvDemo;

    @Override
    protected Object getContentView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView() {
        setHeaderTitle("一些Demo");
        rvDemo = (RecyclerView) findViewById(R.id.rv_demo);
        rvDemo.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {

        final List<ModelHaydraItem> datas = new ArrayList<>();
        datas.add(new ModelHaydraItem("简单实现悬浮", StickyHeadActivity.class));
        datas.add(new ModelHaydraItem("CoordinatorLayout实现悬浮", CoordinatorStickyActivity.class));
        datas.add(new ModelHaydraItem("滑动渐变显示标题栏", TitlebarGradientActivity.class));
        datas.add(new ModelHaydraItem("列表中使用checkbox", CheckboxListActivity.class));
        datas.add(new ModelHaydraItem("截图", ScreenshotActivity.class));
        datas.add(new ModelHaydraItem("RecyclerView拖曳排序,滑动删除",RecyclerViewActivity.class));
        datas.add(new ModelHaydraItem("自定义的圆角布局",CornerLayoutActivity.class));
        datas.add(new ModelHaydraItem("基于腾讯x5查看文档",DocPreviewActivity.class));


        DemoAdapter demoAdapter = new DemoAdapter(R.layout.item_sticky, datas);
        rvDemo.setAdapter(demoAdapter);

        demoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), datas.get(position).getActivity()));
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

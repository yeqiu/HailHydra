package com.yeqiu.hailhaydra.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.OtherListAdapter;
import com.yeqiu.hailhaydra.bean.Model.ModelHaydraItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/10/10
 * @describe：
 * @fix：
 */
public class DemoActivity extends BaseActivity {

    private RecyclerView rlList;
    private List<ModelHaydraItem> data;

    @Override
    protected Object getContentView() {
        return R.layout.activity_other;
    }

    @Override
    protected void initView() {
        setHeaderTitle("Demo");

        rlList = (RecyclerView) findViewById(R.id.rl_list);

    }

    @Override
    protected void initData() {

        data = new ArrayList<>();

        data.add(new ModelHaydraItem("ProgressBar的样式", ProgressBarActivity.class));
        data.add(new ModelHaydraItem("RecyclerView Demo", RecyclerViewDomeActivity.class));
        data.add(new ModelHaydraItem("切换到主线程的工具", ThreadActivity.class));
        data.add(new ModelHaydraItem("当前网络的监听", NetworkListenerActivity.class));
        data.add(new ModelHaydraItem("list中使用checkbox", CheckboxListActivity.class));
        data.add(new ModelHaydraItem("list头部联动", NetworkListenerActivity.class));
        data.add(new ModelHaydraItem("折叠悬浮头", CoordinatorLayoutActivity.class));
        data.add(new ModelHaydraItem("上滑渐变显示标题栏", TitlebarGradientActivity.class));


        rlList.setLayoutManager(new LinearLayoutManager(this, LinearLayout
                .VERTICAL, false));

        OtherListAdapter otherListAdapter = new OtherListAdapter(data);

        rlList.setAdapter(otherListAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}

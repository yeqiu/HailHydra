package com.yeqiu.hailhaydra.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/4/22
 * @describe：
 * @fix：
 */
public class CoordinatorLayoutActivity extends BaseActivity {



    @Override
    protected Object getContentView() {
        return R.layout.activity_coordinatorlayout;
    }

    @Override
    protected void initView() {

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout
                .VERTICAL, false));

        List<String> datas  = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(i+"");
        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(datas);

        recyclerview.setAdapter(recyclerViewAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}

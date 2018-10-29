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
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public class WidgetActivity extends BaseActivity {

    private RecyclerView rlList;
    private List<ModelHaydraItem> data;

    @Override
    protected Object getContentView() {
        return R.layout.activity_other;
    }

    @Override
    protected void initView() {
        setHeaderTitle("自定义控件");

        rlList = (RecyclerView) findViewById(R.id.rl_list);

    }

    @Override
    protected void initData() {

        data = new ArrayList<>();


        data.add(new ModelHaydraItem("自定义的EditText", EditTextActivity.class));
        data.add(new ModelHaydraItem("自定义数字键盘", NumberkeyActivity.class));
        data.add(new ModelHaydraItem("密码框(待改进)", PasswordViewActivity.class));



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

package com.yeqiu.hailhaydra.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.HailHaydraAdapter;
import com.yeqiu.hailhaydra.bean.Model.ModelHaydraItem;
import com.yeqiu.hydrautils.common.DensityUtils;
import com.yeqiu.hydrautils.ui.widget.HaydraRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HailHaydraActivity extends AppCompatActivity {

    @BindView(R.id.rv_haydra)
    RecyclerView mRvHaydra;

    private List<ModelHaydraItem> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hailhaydra);
        ButterKnife.bind(this);


        initData();
        initView();

    }

    private void initView() {
        mRvHaydra.setLayoutManager(new GridLayoutManager(this, 3));
        mRvHaydra.addItemDecoration(new HaydraRecyclerViewDivider(DensityUtils.dp2px(5f)));
        HailHaydraAdapter hailHaydraAdapter = new HailHaydraAdapter(this, data);
        mRvHaydra.setAdapter(hailHaydraAdapter);


    }


    private void initData() {

        data = new ArrayList<>();

        data.add(new ModelHaydraItem("获取设备信息", R.drawable.icon_hydra_1, PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("StringUtils的使用", R.drawable.icon_hydra_2, StringUtilsActivity.class));
        data.add(new ModelHaydraItem("键盘开启关闭的监听", R.drawable.icon_hydra_3, KeyBordListenerActivity.class));
        data.add(new ModelHaydraItem("沉浸式状态栏", R.drawable.icon_hydra_4, PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("仿ios弹窗", R.drawable.icon_hydra_5, DialogActivity.class));
        data.add(new ModelHaydraItem("WebView的封装", R.drawable.icon_hydra_6, WebViewActivity.class));
        data.add(new ModelHaydraItem("WebView的截图", R.drawable.icon_hydra_7, WebViewScreenshotActivity.class));
        data.add(new ModelHaydraItem("ConstraintLayout的炫酷效果", R.drawable.icon_hydra_8, ConstraintLayoutActivity.class));
        data.add(new ModelHaydraItem("LitePal数据库的使用", R.drawable.icon_hydra_9, LitePalActivity.class));
        data.add(new ModelHaydraItem("常用工具", R.drawable.icon_hydra_94, OtherActivity.class));


    }


}

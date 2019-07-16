package com.yeqiu.hydra.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.bean.Model.ModelHaydraItem;
import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.view.activity.demo.sticky.StickyHeadActivity;
import com.yeqiu.hydra.view.adapter.HailHaydraAdapter;
import com.yeqiu.hydra.widget.HydraRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends BaseActivity {

    private RecyclerView rvHaydra;

    private List<ModelHaydraItem> data;


    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        showHeadLayout(false);

        rvHaydra = (RecyclerView) findViewById(R.id.rv_haydra);
    }

    @Override
    protected void initData() {

        data = getData();

        rvHaydra.setLayoutManager(new GridLayoutManager(this, 3));
        rvHaydra.addItemDecoration(new HydraRecyclerViewDivider(DensityUtils.dp2px(5f)));
        HailHaydraAdapter hailHaydraAdapter = new HailHaydraAdapter(this, data);
        rvHaydra.setAdapter(hailHaydraAdapter);

    }

    private List<ModelHaydraItem> getData() {

        List<ModelHaydraItem> data = new ArrayList<>();
        data.add(new ModelHaydraItem("获取设备信息", getRandomId(), PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("base的介绍", getRandomId(), BaseExplainActivity.class));
        data.add(new ModelHaydraItem("app升级和安装", getRandomId(), UpdateActivity.class));
        data.add(new ModelHaydraItem("ios风格的dialog", getRandomId(), DialogActivity.class));
        data.add(new ModelHaydraItem("WebView的封装", getRandomId(), WebViewActivity.class));
        data.add(new ModelHaydraItem("网络请求", getRandomId(), NetActivity.class));
        data.add(new ModelHaydraItem("一些Demo", getRandomId(),DemoActivity.class));

        data.add(new ModelHaydraItem("常见工具类", getRandomId(), StickyHeadActivity.class));
        data.add(new ModelHaydraItem("悬浮窗", getRandomId(), PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("自定义控件", getRandomId(), PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("其他一些功能", getRandomId(), PhoneInfoActivity.class));

        data.add(new ModelHaydraItem("键盘开启关闭的监听", getRandomId(), PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("集成常见的三方", getRandomId(), PhoneInfoActivity.class));

//        data.add(new ModelHaydraItem("StringUtils的使用", getRandomId(), StringUtilsActivity.class));
//        data.add(new ModelHaydraItem("沉浸式状态栏", getRandomId(), ImmersionBarActivity.class));
//        data.add(new ModelHaydraItem("TextTool的使用", getRandomId(), TextToolActivity.class));
//        data.add(new ModelHaydraItem("带图标的Toast", getRandomId(), ToastActivity.class));
//        data.add(new ModelHaydraItem("截图", getRandomId(), ViewScreenshotActivity.class));
//        data.add(new ModelHaydraItem("ConstraintLayout的炫酷效果", getRandomId(),
// ConstraintLayoutActivity.class));
//        data.add(new ModelHaydraItem("Material Design UI", getRandomId(),
// MaterialDesignActivity.class));
//        data.add(new ModelHaydraItem("自定义控件", getRandomId(), WidgetActivity.class));
//        data.add(new ModelHaydraItem("Demo", getRandomId(), DemoActivity.class));
//        data.add(new ModelHaydraItem(" Gson使用详解", getRandomId(), GsonActivity.class));
//

        return data;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }


    private int getRandomId() {

        int id = new Random().nextInt(30) + 1;
        String idName = "icon_head_hydra_" + id;

        int resId = getResources().getIdentifier(idName, "drawable", getPackageName());

        if (resId == 0) {
            resId = R.drawable.hydra;
        }

        return resId;
    }

}
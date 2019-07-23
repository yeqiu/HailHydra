package com.yeqiu.hailhaydra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.HailHaydraAdapter;
import com.yeqiu.hailhaydra.bean.Model.ModelHaydraItem;
import com.yeqiu.hailhaydra.test.ConstraintLayoutTest;
import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.widget.HydraRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HaydraMainActivity extends AppCompatActivity {


    RecyclerView mRvHaydra;

    private List<ModelHaydraItem> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hailhaydra);
        mRvHaydra = (RecyclerView)findViewById(R.id.rv_haydra);


        initData();
        initView();



    }

    private void initView() {
        mRvHaydra.setLayoutManager(new GridLayoutManager(this, 3));
        mRvHaydra.addItemDecoration(new HydraRecyclerViewDivider(DensityUtils.dp2px(5f)));
        HailHaydraAdapter hailHaydraAdapter = new HailHaydraAdapter(this, data);
        mRvHaydra.setAdapter(hailHaydraAdapter);


    }


    private void initData() {

        data = new ArrayList<>();

        data.add(new ModelHaydraItem("获取设备信息", getRandomId(), PhoneInfoActivity.class));
        data.add(new ModelHaydraItem("StringUtils的使用", getRandomId(), StringUtilsActivity.class));
        data.add(new ModelHaydraItem("键盘开启关闭的监听", getRandomId(), KeyBordListenerActivity.class));
        data.add(new ModelHaydraItem("沉浸式状态栏", getRandomId(), ImmersionBarActivity.class));
        data.add(new ModelHaydraItem("仿ios弹窗", getRandomId(), DialogActivity.class));
//        data.add(new ModelHaydraItem("跑马灯TextView", getRandomId(), MarqueeTextViewActivity.class));
        data.add(new ModelHaydraItem("TextTool的使用", getRandomId(), TextToolActivity.class));
        data.add(new ModelHaydraItem("带图标的Toast", getRandomId(), ToastActivity.class));
        data.add(new ModelHaydraItem("WebView的封装", getRandomId(), WebViewActivity.class));
        data.add(new ModelHaydraItem("截图", getRandomId(), ViewScreenshotActivity.class));
        data.add(new ModelHaydraItem("ConstraintLayout的炫酷效果", getRandomId(), ConstraintLayoutActivity.class));
        data.add(new ModelHaydraItem("Material Design UI", getRandomId(), MaterialDesignActivity.class));
        data.add(new ModelHaydraItem("app升级和安装", getRandomId(), UpdateActivity.class));
        data.add(new ModelHaydraItem("自定义控件", getRandomId(), WidgetActivity.class));
        data.add(new ModelHaydraItem("Demo", getRandomId(), DemoActivity.class));
        data.add(new ModelHaydraItem(" Gson使用详解", getRandomId(), GsonActivity.class));
        data.add(new ModelHaydraItem(" 网络请求", getRandomId(),NetActivity.class));


        startActivity(new Intent(this, ConstraintLayoutTest.class));

    }


    private int getRandomId() {

        int id = new Random().nextInt(30) + 1;
        String idName = "icon_head_hydra_" + id;

        int resId = getResources().getIdentifier(idName, "drawable", getPackageName());

        if (resId == 0){
            resId = R.drawable.hydra;
        }

        return resId;
    }


}

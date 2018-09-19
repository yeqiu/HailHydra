package com.yeqiu.hailhaydra.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.HailHaydraAdapter;
import com.yeqiu.hailhaydra.bean.Model.ModelHaydraItem;
import com.yeqiu.hydrautils.common.DensityUtils;
import com.yeqiu.hydrautils.common.LogUtils;
import com.yeqiu.hydrautils.ui.widget.HydraRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HaydraMainActivity extends AppCompatActivity {

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
        data.add(new ModelHaydraItem("跑马灯TextView", getRandomId(), MarqueeTextViewActivity.class));
        data.add(new ModelHaydraItem("TextTool的使用", getRandomId(),TextToolActivity.class));
        data.add(new ModelHaydraItem("带图标的Toast", getRandomId(), ToastActivity.class));
        data.add(new ModelHaydraItem("WebView的封装", getRandomId(), WebViewActivity.class));
        data.add(new ModelHaydraItem("截图", getRandomId(), ViewScreenshotActivity.class));
        data.add(new ModelHaydraItem("ConstraintLayout的炫酷效果", getRandomId(), ConstraintLayoutActivity.class));
        data.add(new ModelHaydraItem("视频播放", getRandomId(), VideoPlayActivity.class));
        data.add(new ModelHaydraItem("app升级和安装", getRandomId(), UpdateActivity.class));
        data.add(new ModelHaydraItem("常用工具", getRandomId(), OtherActivity.class));


    }


    private int getRandomId() {

        int id = new Random().nextInt(30) + 1;
        String idName = "icon_head_hydra_" + id;

        int resId = getResources().getIdentifier(idName, "drawable", getPackageName());


        LogUtils.i("idName = "+idName);
//        if (resId == 0){
//            resId = R.drawable.hydra;
//        }

        return resId;
    }


}

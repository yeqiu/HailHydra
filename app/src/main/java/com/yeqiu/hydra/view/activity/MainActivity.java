package com.yeqiu.hydra.view.activity;

import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.bean.model.ModelHaydraItem;
import com.yeqiu.hydra.utils.ToastUtils;
import com.yeqiu.hydra.view.activity.demo.DemoActivity;
import com.yeqiu.hydra.view.activity.utils.UtilsActivity;
import com.yeqiu.hydra.view.adapter.HailHaydraAdapter;
import com.yeqiu.hydra.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/15
 * @describe：
 * @fix：
 */
public class MainActivity extends BaseActivity {

    private RecyclerView rvHaydra;

    private List<ModelHaydraItem> data;
    private long firstTime = 0;

    @Override
    protected boolean isShowActivityAnimation() {
        return false;
    }

    @Override
    protected Object getContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected boolean isSwipeBack() {
        return false;
    }



    @Override
    protected void initView() {

        showHeadBar(false);

        rvHaydra = (RecyclerView) findViewById(R.id.rv_haydra);
    }

    @Override
    public void initData() {

        data = getData();

        rvHaydra.setLayoutManager(new GridLayoutManager(this, 3));

        //使用空白分割线
        RecyclerViewDivider recyclerViewDivider =
                new RecyclerViewDivider(RecyclerViewDivider.MODE_GRID)
                        .setDividerColor(R.color.transparent)
                        .setSpace(10);

        rvHaydra.addItemDecoration(recyclerViewDivider);

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
        data.add(new ModelHaydraItem("一些Demo", getRandomId(), DemoActivity.class));
        data.add(new ModelHaydraItem("工具类", getRandomId(), UtilsActivity.class));
        data.add(new ModelHaydraItem("StatusLayout的使用", getRandomId(), StatusLayoutActivity.class));
        data.add(new ModelHaydraItem("通知的一些写法", getRandomId(), NotificationActivity.class));
        data.add(new ModelHaydraItem("集成常见三方SDK", getRandomId(), SDKActivity.class));


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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    ToastUtils.showToast("再按一次退出程序");
                    firstTime = secondTime;
                    return true;
                } else {
                    finish();
                    System.exit(0);
                }
                break;

            default:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


}
package com.yeqiu.hailhydra.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeqiu.androiddome.R;
import com.yeqiu.hailhydra.adapter.SimpleAdapter;
import com.yeqiu.hailhydra.data.DomeData;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ListView domeList;
    private ArrayList<DomeData> datas;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        super.initView();
        setHeaderTitle("MainActivity");
        domeList = (ListView) findViewById(R.id.dome_list);
        setHomeData();

    }

    private void setHomeData() {


        datas = new ArrayList<>();
        datas.add(new DomeData("BaseActivity", SampleActivity.class));
        datas.add(new DomeData("StatusLayout", StatusLayoutActivity.class));
        datas.add(new DomeData("沉浸式状态", ImmersivetestActivity.class));
        datas.add(new DomeData("键盘监听", SoftKeyListenerActivity.class));
        datas.add(new DomeData("自定义数字键盘 1", NumberkeyActivity.class));
        datas.add(new DomeData("自定义数字键盘 2", NumberkeyActivity2.class));
        datas.add(new DomeData("密码框 ", PasswordViewActivity.class));
        datas.add(new DomeData("EditText ", EditTextActivity.class));
        datas.add(new DomeData("ios风格的Diaog ", DialogActivity.class));
        datas.add(new DomeData("网络监听 ", NetworkListenerActivity.class));
        datas.add(new DomeData("RecyclerView Demo ", RecyclerViewDomeActivity.class));
        datas.add(new DomeData("OKGo的封装", OKGoActivity.class));
        datas.add(new DomeData("WebView的使用", WebViewActivity.class));
        datas.add(new DomeData("ProgressBar", ProgressBarActivity.class));
        datas.add(new DomeData("webview截长图", WebViewScreenshotActivity.class));
        datas.add(new DomeData("切换主线程", ThreadActivity.class));


    }

    @Override
    protected void initData() {
        domeList.setCacheColorHint(Color.TRANSPARENT);
        domeList.setFadingEdgeLength(0);
        domeList.setAdapter(new SimpleAdapter(this, datas));

        domeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Class activity = datas.get(position).getActivity();
                Intent intent = new Intent(MainActivity.this, activity);
                startActivity(intent);
            }
        });
    }


}

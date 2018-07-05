package com.yeqiu.androidlibrary.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.yeqiu.androiddome.R;
import com.yeqiu.androidlibrary.adapter.SimpleAdapter;
import com.yeqiu.androidlibrary.data.DomeData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView domeList;
    private ArrayList<DomeData> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        domeList = (ListView) findViewById(R.id.dome_list);
        setHomeData();

        initData();

        testLog();

    }

    private void setHomeData() {


        datas = new ArrayList<>();
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


    }

    private void initData() {

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


    private void testLog() {
        Logger.init("test")              //设置tag
                .logLevel(LogLevel.FULL) //显示全部日志，LogLevel.NONE不显示日志，默认是Full
                .methodCount(1)          //方法栈打印的个数，默认是2
                .methodOffset(0);        //设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0


        Logger.i("测试数据1");


        String json = "{\n" +
                "    \"code\": 401,\n" +
                "    \"message\": \"用户尚未登录\",\n" +
                "    \"data\": {\n" +
                "        \"audit_amount\": 10000\n" +
                "    }\n" +
                "}";


        Logger.json(json);


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        Logger.d(list);  //只能使用d  不要用 d(String message, Object... args) 没卵用



    }
}

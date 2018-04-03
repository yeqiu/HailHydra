package com.yeqiu.androiddome.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeqiu.androiddome.R;
import com.yeqiu.androiddome.adapter.SimpleAdapter;
import com.yeqiu.androiddome.data.DomeData;

import java.util.ArrayList;

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

    }

    private void setHomeData() {



        datas = new ArrayList<>();
        datas.add(new DomeData("沉浸式状态", ImmersivetestActivity.class));
        datas.add(new DomeData("键盘监听", SoftKeyListenerActivity.class));
        datas.add(new DomeData("自定义数字键盘 1", NumberkeyActivity.class));


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
}

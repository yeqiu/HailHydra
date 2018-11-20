package com.yeqiu.hailhaydra;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/15
 * @describe：
 * @fix：
 */
public class test extends Activity {


    CheckBox cbItem;
    TextView tvItemName;
    TextView tvItemNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_checkbox_list);
    }

    private void onInitView() {
        cbItem = (CheckBox) findViewById(R.id.cb_item);
        tvItemName = (TextView) findViewById(R.id.tv_item_name);
        tvItemNumber = (TextView) findViewById(R.id.tv_item_number);
    }
}

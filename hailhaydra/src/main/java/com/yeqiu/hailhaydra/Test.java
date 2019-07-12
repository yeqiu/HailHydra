package com.yeqiu.hailhaydra;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeqiu.hailhaydra.test.B;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/15
 * @describe：
 * @fix：
 */
public class Test extends Activity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_checkbox_list);
    }

    private void onInitView() {

        new B().funA().funB();






    }
}

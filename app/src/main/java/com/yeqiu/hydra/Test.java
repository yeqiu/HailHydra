package com.yeqiu.hydra;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/2
 * @describe：
 * @fix：
 */
public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pager_grid_dialog);


    }


    public void text() {
        List<Button> buttons = new ArrayList<>();
        fun(buttons);
    }


    public void fun(List<? extends TextView> list) {

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setText("text");
        }
    }


}

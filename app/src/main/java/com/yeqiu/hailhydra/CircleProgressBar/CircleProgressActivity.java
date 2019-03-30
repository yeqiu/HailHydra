package com.yeqiu.hailhydra.CircleProgressBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yeqiu.hailhydra.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/3/29
 * @describe：
 * @fix：
 */
public class CircleProgressActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);

        CircleProgressBar CircleProgressBar = (CircleProgressBar) findViewById(R.id.CircleProgressBar);


        CircleProgressBar.setProgress(10);
    }
}

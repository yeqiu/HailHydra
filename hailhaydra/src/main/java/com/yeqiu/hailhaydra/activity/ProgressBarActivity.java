package com.yeqiu.hailhaydra.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.app.HailHaydraApp;
import com.yeqiu.hydra.utils.LogUtils;

import java.util.List;


/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/6
 * @describe：
 * @fix：
 */
public class ProgressBarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        List<Activity> activities = HailHaydraApp.getInstance().getActivities();

        for (int i = 0; i < activities.size(); i++) {
            String localClassName = activities.get(i).getLocalClassName();

            LogUtils.i("name = " + localClassName);
        }


    }
}

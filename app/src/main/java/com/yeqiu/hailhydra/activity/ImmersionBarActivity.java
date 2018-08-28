package com.yeqiu.hailhydra.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.utils.StatusBarUtils;

public class ImmersionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_immersion_bar);

        StatusBarUtils.with(this)
                .setColor(getResources().getColor(R.color.color_0d95ff))
//                .setDrawable(getResources().getDrawable(R.drawable.shape))
                .init();
    }
}

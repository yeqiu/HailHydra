package com.yeqiu.hailhydra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yeqiu.hailhydra.R;

/**
 * @author ye
 * @date 2018/4/2
 * @desc 沉浸式状态栏
 * https://www.jianshu.com/p/29bf603359d6
 */
public class ImmersivetestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersivetest);


        //隐藏 ActionBar

        findViewById(R.id.immersion_img_btn).setOnClickListener(this);
        findViewById(R.id.immersion_normal_btn).setOnClickListener(this);
        findViewById(R.id.immersion_nav_btn).setOnClickListener(this);
        findViewById(R.id.immersion_actionbar_btn).setOnClickListener(this);


    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.immersion_img_btn:
                startActivity(new Intent(this, ImmersionImageActivity.class));
                break;
            case R.id.immersion_normal_btn:
                startActivity(new Intent(this, ImmersionBarActivity.class));
                break;
            case R.id.immersion_nav_btn:


                break;
            case R.id.immersion_actionbar_btn:

                break;
            default:
                break;
        }
    }


}

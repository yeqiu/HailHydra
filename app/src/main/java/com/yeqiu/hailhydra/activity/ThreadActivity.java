package com.yeqiu.hailhydra.activity;

import android.view.View;
import android.widget.Button;

import com.yeqiu.androiddome.R;
import com.yeqiu.hailhydra.utils.ThreadUtils.ThreadUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/15
 * @describe：
 * @fix：
 */
public class ThreadActivity extends BaseActivity {

    private Button thread;

    @Override
    protected int getContentView() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initData() {


        thread = (Button) findViewById(R.id.thread);


        thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread();
            }
        });



    }

    private void thread() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                //子线程

                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        thread.setText("子线程切换修改");
                    }
                });


            }
        }.start();
    }
}

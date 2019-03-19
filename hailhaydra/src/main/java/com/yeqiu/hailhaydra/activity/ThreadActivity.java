package com.yeqiu.hailhaydra.activity;

import android.view.View;
import android.widget.Button;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.thread.ThreadUtils;


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
    protected Object getContentView() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initView() {

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

    @Override
    protected void initListener() {

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
                        thread.setText("子线程中切换到主线程");
                    }
                });


            }
        }.start();
    }

    @Override
    public void onClick(View v) {

    }
}

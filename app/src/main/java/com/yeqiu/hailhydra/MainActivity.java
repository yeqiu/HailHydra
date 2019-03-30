package com.yeqiu.hailhydra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yeqiu.hailhydra.CircleProgressBar.CircleProgressActivity;
import com.yeqiu.hailhydra.MediaPlay.MediaPlayerUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager vp;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView add3;
    TextView del3;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TestAdapter testAdapter;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onInitView();


        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);


        testAdapter = new TestAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(testAdapter);



        startActivity(new Intent(this, CircleProgressActivity.class));


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerUtil.getMediaPlayer().stopPlay();
    }

    private void onInitView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        add3 = (TextView) findViewById(R.id.add3);
        del3 = (TextView) findViewById(R.id.del3);
        onInitListener();
    }

    private void onInitListener() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        add3.setOnClickListener(this);
        del3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                vp.setCurrentItem(0);
                break;
            case R.id.tv_2:
                vp.setCurrentItem(1);
                break;
            case R.id.tv_3:
                vp.setCurrentItem(2);
                break;
            case R.id.tv_4:
                vp.setCurrentItem(3);
                break;
            case R.id.add3:

                add();
                break;
            case R.id.del3:
                del();
                break;

            default:
                break;
        }
    }

    private void del() {


        if (fragments.contains(fragment3)) {
            fragments.remove(fragment3);
            testAdapter.notifyDataSetChanged();
        }


    }

    private void add() {

        if (!fragments.contains(fragment3)) {
            fragments.add(2, fragment3);
            testAdapter.notifyDataSetChanged();
        }


    }


}

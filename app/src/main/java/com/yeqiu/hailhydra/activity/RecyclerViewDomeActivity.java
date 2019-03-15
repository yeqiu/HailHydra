package com.yeqiu.hailhydra.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yeqiu.hailhydra.GridLayoutDecoration;
import com.yeqiu.hailhydra.LinearLayoutDecoration;
import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/6/26
 * @describe：
 * @fix：
 */
public class RecyclerViewDomeActivity extends AppCompatActivity implements View.OnClickListener {


    Button btRecyclerviewDomeHorizontal;
    Button btRecyclerviewDomeVertical;
    Button btRecyclerviewDomeGrid;
    Button btRecyclerviewDomePinterest;
    RecyclerView recyclerview;


    private ArrayList<Integer> img = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayout llHead;

    private int postion = 0;
    //悬浮条的高度
    private float headerHight;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_dome);


        btRecyclerviewDomeHorizontal = (Button) findViewById(R.id.bt_recyclerview_dome_horizontal);
        btRecyclerviewDomeVertical = (Button) findViewById(R.id.bt_recyclerview_dome_vertical);
        btRecyclerviewDomeGrid = (Button) findViewById(R.id.bt_recyclerview_dome_grid);
        btRecyclerviewDomePinterest = (Button) findViewById(R.id.bt_recyclerview_dome_pinterest);
        recyclerview = (RecyclerView) findViewById(R.id.rc_recyclerview_dome_recyclerview);
        llHead = (LinearLayout) findViewById(R.id.ll_head);


        btRecyclerviewDomeHorizontal.setOnClickListener(this);
        btRecyclerviewDomeVertical.setOnClickListener(this);
        btRecyclerviewDomeGrid.setOnClickListener(this);
        btRecyclerviewDomePinterest.setOnClickListener(this);

        initData();
        initRecyclerview(2);

        initListener();

    }


    private void initData() {

        for (int i = 0; i < 10; i++) {
            img.add(R.drawable.sishen);
            img.add(R.drawable.yinhun);
        }

    }


    private void initListener() {




    }

    /**
     * @param type 1:横向 2:垂直 3:网格 4:瀑布流
     */
    private void initRecyclerview(int type) {

        //第一个参数为上下文环境，第二个参数为布局显示方式，第三个参数为布尔值是否反转 (即滑动的方向 多数情况使用false)
        linearLayoutManager = new LinearLayoutManager(this, LinearLayout
                .VERTICAL, false);


        // recyclerview.addItemDecoration(new Decoration(this));

        LinearLayoutDecoration linearLayoutDecoration = new LinearLayoutDecoration();


        GridLayoutDecoration gridLayoutDecoration = new GridLayoutDecoration();


        switch (type) {

            case 1:
                linearLayoutDecoration.setOrientation(LinearLayoutDecoration.HORIZONTAL);
                recyclerview.setLayoutManager(linearLayoutManager);
                break;
            case 2:
                linearLayoutDecoration.setOrientation(LinearLayoutDecoration.VERTICAL);
                recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout
                        .VERTICAL, false));
                break;
            case 3:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

                recyclerview.setLayoutManager(gridLayoutManager);


                //设置为一个3列的纵向网格布局
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3,
                        GridLayoutManager.VERTICAL, false);


                break;
            case 4:

                StaggeredGridLayoutManager staggeredGridLayoutManager = new
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

                recyclerview.setLayoutManager(staggeredGridLayoutManager);



                break;

            default:

                break;


        }
        //   recyclerview.addItemDecoration(gridLayoutDecoration);

        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = new RecyclerViewAdapter(this, img);
        } else {
            recyclerViewAdapter.setType(type);
        }


        recyclerview.setAdapter(recyclerViewAdapter);

        recyclerview.scrollToPosition(1);





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_recyclerview_dome_horizontal:
                initRecyclerview(1);
                break;
            case R.id.bt_recyclerview_dome_vertical:
                initRecyclerview(2);
                break;
            case R.id.bt_recyclerview_dome_grid:
                initRecyclerview(3);
                break;
            case R.id.bt_recyclerview_dome_pinterest:
                initRecyclerview(4);
                break;
        }

    }
}

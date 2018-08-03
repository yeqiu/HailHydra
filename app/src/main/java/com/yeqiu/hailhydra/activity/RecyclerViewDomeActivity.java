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

import com.yeqiu.androiddome.R;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_dome);


        btRecyclerviewDomeHorizontal = (Button) findViewById(R.id.bt_recyclerview_dome_horizontal);
        btRecyclerviewDomeVertical = (Button) findViewById(R.id.bt_recyclerview_dome_vertical);
        btRecyclerviewDomeGrid = (Button) findViewById(R.id.bt_recyclerview_dome_grid);
        btRecyclerviewDomePinterest = (Button) findViewById(R.id.bt_recyclerview_dome_pinterest);
        recyclerview = (RecyclerView) findViewById(R.id.rc_recyclerview_dome_recyclerview);

        btRecyclerviewDomeHorizontal.setOnClickListener(this);
        btRecyclerviewDomeVertical.setOnClickListener(this);
        btRecyclerviewDomeGrid.setOnClickListener(this);
        btRecyclerviewDomePinterest.setOnClickListener(this);

        initData();
        initRecyclerview(1);

    }


    private void initData() {

        for (int i = 0; i < 10; i++) {
            img.add(R.drawable.sishen);
            img.add(R.drawable.yinhun);
        }


    }


    /**
     * @param type 1:横向 2:垂直 3:网格 4:瀑布流
     */
    private void initRecyclerview(int type) {

        //第一个参数为上下文环境，第二个参数为布局显示方式，第三个参数为布尔值是否反转 (即滑动的方向 多数情况使用false)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout
                .HORIZONTAL, false);



        switch (type) {

            case 1:

                recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout
                        .HORIZONTAL, false));
                break;
            case 2:

                recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout
                        .VERTICAL, false));
                break;
            case 3:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

                recyclerview.setLayoutManager(gridLayoutManager);


                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3,
                        GridLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局



                break;
            case 4:

                StaggeredGridLayoutManager staggeredGridLayoutManager = new
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

                recyclerview.setLayoutManager(staggeredGridLayoutManager);


                break;


        }


        if (recyclerViewAdapter==null){
            recyclerViewAdapter  = new RecyclerViewAdapter(this,img);
        }else{
            recyclerViewAdapter.setType(type);
        }


        recyclerview.setAdapter(recyclerViewAdapter);



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

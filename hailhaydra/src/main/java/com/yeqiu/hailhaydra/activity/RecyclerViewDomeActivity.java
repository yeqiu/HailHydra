package com.yeqiu.hailhaydra.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.RecyclerViewAdapter;
import com.yeqiu.hailhaydra.view.GridDecoration;
import com.yeqiu.hailhaydra.view.LinearDecoration;

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


    private ArrayList<String> datas = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private LinearDecoration linearDecoration;
    private GridDecoration gridDecoration;


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

        for (int i = 'A'; i < 'z'; i++) {
            datas.add("" + (char) i);
        }

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 4);
        linearDecoration = new LinearDecoration(this);
        gridDecoration = new GridDecoration(this);




    }


    /**
     * @param type 1:横向 2:垂直 3:网格 4:瀑布流
     */
    private void initRecyclerview(int type) {


        recyclerview.removeItemDecoration(linearDecoration);
        recyclerview.removeItemDecoration(gridDecoration);


        switch (type) {

            case 1:

                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                linearDecoration.setOrientation(LinearDecoration.HORIZONTAL_LIST);
                recyclerview.setLayoutManager(linearLayoutManager);
                recyclerview.addItemDecoration(linearDecoration);


                break;
            case 2:

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                linearDecoration.setOrientation(LinearDecoration.VERTICAL_LIST);
                recyclerview.setLayoutManager(linearLayoutManager);
                recyclerview.addItemDecoration(linearDecoration);


                break;
            case 3:

                recyclerview.setLayoutManager(gridLayoutManager);
                recyclerview.addItemDecoration(gridDecoration);

                break;
            case 4:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                        (1,StaggeredGridLayoutManager.HORIZONTAL);

                recyclerview.setLayoutManager(staggeredGridLayoutManager);
                recyclerview.addItemDecoration(gridDecoration);
                break;
            default:
                break;
        }


        if (recyclerViewAdapter == null) {
            recyclerViewAdapter = new RecyclerViewAdapter(this, datas);
            recyclerview.setAdapter(recyclerViewAdapter);
        } else {
            recyclerViewAdapter.setType(type);
        }


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

            default:
                break;
        }

    }
}

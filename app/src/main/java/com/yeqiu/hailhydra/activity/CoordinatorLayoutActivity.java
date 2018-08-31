package com.yeqiu.hailhydra.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hailhydra.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/30
 * @describe：
 * @fix：
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout);


        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayout
                .VERTICAL, false));

        List<String> datas  = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(i+"");
        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(datas);

        recyclerview.setAdapter(recyclerViewAdapter);

    }




    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


        private List<String> datas;

        public RecyclerViewAdapter(List<String> datas) {
            this.datas = datas;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_recyclerview_for_coordinatorlayout, null);

            ViewHolder viewHolder = new ViewHolder(view);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String s = datas.get(position);

            holder.tv.setText(s);

        }

        @Override
        public int getItemCount() {
            return datas.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {



            private TextView tv;

            public ViewHolder(View itemView) {
                super(itemView);

                tv = (TextView) itemView.findViewById(R.id.tv_item_coordinatorlayout);
            }
        }

    }
}

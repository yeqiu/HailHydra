package com.yeqiu.hydra.view.activity.demo;

import android.graphics.Color;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.ToastUtils;
import com.yeqiu.hydra.utils.image.ImageUtils;
import com.yeqiu.hydra.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/18
 * @describe： https://juejin.im/post/5a320ffcf265da43200342a3#heading-7
 * https://blog.csdn.net/yanzhenjie1003/article/details/51935982
 * @fix：
 */
public class RecyclerViewActivity extends BaseActivity {


    private RecyclerView rvRecyclerView;
    private List<String> datas;
    private SimpleAdapter simpleAdapter;


    @Override
    protected Object getContentView() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void initView() {

        setHeaderTitle("RecyclerView");
        rvRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {


        datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("RecyclerView测试数据" + i);
        }

        simpleAdapter = new SimpleAdapter(R.layout.item_recycler_view, datas);
        rvRecyclerView.setAdapter(simpleAdapter);

        simpleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(datas.get(position));
            }
        });


        //添加分割线
        rvRecyclerView.addItemDecoration(new DividerListItemDecoration(getActivity(),
                DividerListItemDecoration.VERTICAL_LIST));


        //拖拽
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvRecyclerView);


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }


    class SimpleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SimpleAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

            helper.setText(R.id.tv_item_recycler_view, item);

            ImageView iv = helper.getView(R.id.iv_item_recycler_view);

            new ImageUtils()
                    .load(mContext, getRandomId(), iv);

        }


        private int getRandomId() {

            int id = new Random().nextInt(30) + 1;
            String idName = "icon_head_hydra_" + id;

            int resId = getResources().getIdentifier(idName, "drawable", getPackageName());

            if (resId == 0) {
                resId = R.drawable.hydra;
            }

            return resId;
        }

    }


    private ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {

        /**
         * 通过返回值来设置是否处理某次拖曳或者滑动事件
         * @param recyclerView
         * @param viewHolder
         * @return
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                //注意：和拖曳的区别就是在这里
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }


        /**
         * 当长按并进入拖曳状态时，拖曳的过程中不断的回调此方法
         * @param recyclerView
         * @param viewHolder
         * @param target
         * @return
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {

            //拖动的 item 的下标
            int fromPosition = viewHolder.getAdapterPosition();
            //目标 item 的下标，目标 item 就是当拖曳过程中，不断和拖动的 item 做位置交换的条目。
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(datas, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(datas, i, i - 1);
                }
            }
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        /**
         * 滑动删除的回调
         * @param viewHolder
         * @param direction
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {


            int adapterPosition = viewHolder.getAdapterPosition();
            simpleAdapter.notifyItemRemoved(adapterPosition);
            datas.remove(adapterPosition);
        }


        //当长按 item 刚开始拖曳的时候调用
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                //给被拖曳的 item 设置一个深颜色背景
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        //当完成拖曳手指松开的时候调用
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            //给已经完成拖曳的 item 恢复开始的背景。
            //这里我们设置的颜色尽量和你 item 在 xml 中设置的颜色保持一致
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }


    };

}

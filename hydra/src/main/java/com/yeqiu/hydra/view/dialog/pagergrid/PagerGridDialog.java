package com.yeqiu.hydra.view.dialog.pagergrid;

import android.app.Activity;
import androidx.viewpager.widget.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydra.view.dialog.bean.ListData;
import com.yeqiu.hydra.widget.FullGridView;
import com.yeqiu.hydrautils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/12
 * @describe：viewpager+grid 底部弹框（分享弹框）
 * @fix：
 */
public class PagerGridDialog extends HydraBaseDialog<PagerGridDialog> {


    private ViewPager vpPagerGrid;
    private List<GridView> gridViews;
    private LinearLayout llDot;
    private List<ListData> datas;
    private int pageCount;

    private int pageSize = 8;
    private int numColumns = 4;
    public int selectedIndictor = R.drawable.shape_dot_selected;
    public int unselectedIndictor = R.drawable.shape_dot_normal;


    public PagerGridDialog(Activity context) {
        super(context);

    }


    @Override
    protected void setWindow() {

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);


    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_pager_grid_dialog;
    }

    @Override
    protected void initDialog(View view) {


        vpPagerGrid = (ViewPager) findViewById(R.id.vp_pager_grid);
        llDot = (LinearLayout) findViewById(R.id.ll_pager_grid_dot);

        //计算总页数
        pageCount = (int) Math.ceil(getDatas().size() * 1.0 / pageSize);
        //获取一行的个数
        numColumns = getDatas().size() < numColumns ? getDatas().size() : numColumns;
        //计算分配多少行
        int line = (int) Math.ceil(pageSize * 1.0 / numColumns);
        //动态设置viewpager的高度
        View itemView = View.inflate(getContext(), R.layout.item_grid_pager, null);
        itemView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int measuredHeight = itemView.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vpPagerGrid
                .getLayoutParams();

        layoutParams.height = pageCount > 1 ? measuredHeight * line : measuredHeight;
        vpPagerGrid.setLayoutParams(layoutParams);


        gridViews = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {

            FullGridView gridView = new FullGridView(getContext());
            gridView.setBackgroundColor(ResourceUtil.getColor(R.color.color_white));
            gridView.setNumColumns(numColumns);
            gridView.setOverScrollMode(GridView.OVER_SCROLL_NEVER);
            gridView.setAdapter(new GridPagerAdapter(getDatas(), i, pageSize));
            gridViews.add(gridView);
            setOnItemClickListener(gridView, i);

            //添加圆点 超过一页才会添加圆点
            if (pageCount > 1) {
                ImageView ivDot = new ImageView(getContext());
                ivDot.setImageResource(unselectedIndictor);
                llDot.addView(ivDot);
                ivDot.getLayoutParams();
                LinearLayout.LayoutParams dotLayoutParams = new LinearLayout.LayoutParams(ivDot
                        .getLayoutParams());
                dotLayoutParams.setMargins(2, 2, 2, 2);
                ivDot.setLayoutParams(dotLayoutParams);
            }

        }

        vpPagerGrid.setAdapter(new PagerGridAdapter(gridViews));

        vpPagerGrid.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentItem(position);
            }
        });
        setCurrentItem(0);

    }

    private void setOnItemClickListener(FullGridView gridView, final int index) {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (getDialogListener() != null) {
                    position = position + index * pageSize;
                    String title = getDatas().get(position).getTitle();
                    getDialogListener().onItemClick(position, title);
                }
                dismissDialogWhitDelayer();

            }
        });
    }


    private void setCurrentItem(int position) {

        for (int i = 0; i < llDot.getChildCount(); i++) {
            ImageView ivDot = (ImageView) llDot.getChildAt(i);
            ivDot.setImageResource(i == position ? selectedIndictor : unselectedIndictor);

        }


    }


    //==========设置数据==========


    /**
     * 列表弹框的列表数据
     */
    public PagerGridDialog setData(List<ListData> datas) {
        this.datas = datas;
        return this;
    }


    /**
     * 设置一页的个数
     *
     * @param pageSize
     * @return
     */
    public PagerGridDialog setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    /**
     * 设置一行的个数
     *
     * @param numColumns
     * @return
     */
    public PagerGridDialog setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        return this;
    }

    /**
     * 设置选中指示器样式
     *
     * @param selectedIndictor
     * @return
     */
    public PagerGridDialog setSelectedIndictor(int selectedIndictor) {
        this.selectedIndictor = selectedIndictor;
        return this;
    }

    /**
     * 设置未选中指示器样式
     *
     * @param unselectedIndictor
     * @return
     */
    public PagerGridDialog setUnselectedIndictor(int unselectedIndictor) {
        this.unselectedIndictor = unselectedIndictor;
        return this;
    }


    //==========get()==========


    public List<ListData> getDatas() {
        if (datas == null) {
            return new ArrayList<>();
        }
        return datas;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getSelectedIndictor() {
        return selectedIndictor;
    }

    public int getUnselectedIndictor() {
        return unselectedIndictor;
    }
}

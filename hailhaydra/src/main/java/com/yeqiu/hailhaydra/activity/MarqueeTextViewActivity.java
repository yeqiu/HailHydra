package com.yeqiu.hailhaydra.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydrautils.HydraUtilsManager;
import com.yeqiu.hydrautils.common.UIHelper;
import com.yeqiu.hydrautils.widget.marquee.MarqueeTextView;
import com.yeqiu.hydrautils.widget.marquee.MarqueeVerticalView;
import com.yeqiu.hydrautils.widget.marquee.MarqueeVerticalWhitIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/17
 * @describe：
 * @fix：
 */
public class MarqueeTextViewActivity extends BaseActivity {

    private MarqueeTextView mMtvTextview;
    private MarqueeVerticalView marqueeVerticalView;
    private MarqueeVerticalWhitIcon marqueeVerticalWhitIcon;


    private ArrayList<String> texts = new ArrayList<>();

    @Override
    protected Object getContentView() {
        return R.layout.activity_marquee_text;
    }

    @Override
    protected void initView() {
        setHeaderTitle("跑马灯TextView");

        mMtvTextview = (MarqueeTextView) findViewById(R.id.mtv_textview);

        marqueeVerticalView = (MarqueeVerticalView) findViewById(R.id.marqueeverticalview);

        marqueeVerticalWhitIcon = (MarqueeVerticalWhitIcon) findViewById(R.id.marqueeverticalwhiticon);
    }


    @Override
    protected void onResume() {
        super.onResume();
        marqueeVerticalView.startAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        marqueeVerticalView.stopAutoScroll();
    }


    @Override
    protected void initData() {

        texts.add("凤兮凤兮归故乡，遨游四海求其凰。");
        texts.add("三尺长剑，斩不尽相思情缠。");
        texts.add(" 邂逅你，是生生世世的宿命。");
        texts.add(" 逆了苍天，踏破碧落黄泉。");
        texts.add(" 直至地老天荒，独剩你我。");
        texts.add("剑之所至，心之所往。");
        texts.add("单身又活的太久是最大的痛，我来替你解脱！");
        texts.add("长歌当哭，为君仗剑弑天下！");
        texts.add("永生不过是场幻梦，唯吾所爱不朽。");
        texts.add("何以缘起？何以缘灭？当以剑歌问之。");

        marqueeVerticalView.setTextList(texts);
        //设置属性
        marqueeVerticalView.setText(26, 0, HydraUtilsManager.getInstance().getContext().getResources().getColor(R.color
                .color_0d95ff));
        //设置停留时长间隔
        marqueeVerticalView.setTextStillTime(3000);
        //设置进入和退出的时间间隔
        marqueeVerticalView.setAnimTime(300);
        marqueeVerticalView.setOnItemClickListener(new MarqueeVerticalView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIHelper.showToast(texts.get(position));
            }
        });


        ///注意：要在onResume调用stopAutoScroll才能滚动

// ---------------------------------------------------------------------------------------------------------------------


        List<View> views = new ArrayList<>();
        setUPMarqueeView(views, texts.size());
        marqueeVerticalWhitIcon.setViews(views);


    }

    @Override
    protected void initListener() {

    }


    private void setUPMarqueeView(List<View> views, int size) {
        for (int i = 0; i < size; i = i + 2) {
            final int position = i;

            //设置滚动的单个布局
            final LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout
                    .item_marqueeverticalwhiticon_view, null);
            //初始化布局的控件
            TextView tv1 = moreView.findViewById(R.id.tv1);
            TextView tv2 = moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UIHelper.showToast(texts.get(position));

                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UIHelper.showToast(texts.get(position + 1));
                }
            });


            //进行对控件赋值
            tv1.setText(texts.get(i));
            if (size > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(texts.get(i + 1));
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }


    @Override
    public void onClick(View v) {

    }


}

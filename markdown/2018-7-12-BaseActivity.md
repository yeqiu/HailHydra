---
layout:     post  
title:      BaseActivity 
subtitle:   BaseActivity的封装
date:       2018-7-12
author:     小卷子
header-img: 这篇文章标题背景图片
catalog: true
tags:
    - BaseActivity
---




## BaseActivity的封装

>绝大多数项目里都会有一个BaseActivity，用来抽取共性，封装统一操作。



**多状态布局 标题栏 沉浸式等 **

在BaseActivity中引用StatusLayout，处理好空页面，错误页面的显示。统一的标题栏，设置显示或隐藏。

直接上代码:

~~~java
public abstract class BaseActivity extends AppCompatActivity {
    
    private StatusLayout statusLayout;
    private LinearLayout headLayout;
    public ImageView headBack;
    public TextView headerTitle;
    public TextView tvheaderRight;
    public ImageView ivheaderRight;
    private View headLine;

    public Activity context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        statusLayout = (StatusLayout) findViewById(R.id.base_status_layout);
        statusLayout.setContentView(getContentView());
        statusLayout.showContentView();

        context = this;
        //添加到activity管理器
        AppManager.getAppManager().addActivity(this);
        //统一处理 ButterKnife
        ButterKnife.bind(this);
        //子类可能需要进行的操作
        init();

        statusLayout.setOnStatusLayoutClickListener(onStatusLayoutClickListener);

    }


    private void init() {
        initHead();
        initStatusBar();
        initView();
        initData();
        initListener();
        registerEventBus();
    }


    /**
     * 初始化head标题栏
     * 标题栏中包括 返回 标题 右侧文字 图片1  图片2
     */
    private void initHead() {
        headLayout = (LinearLayout) findViewById(R.id.ll_common_header_layout);
        headBack = (ImageView) findViewById(R.id.iv_common_head_back);
        headerTitle = (TextView) findViewById(R.id.tv_common_head_title);
        tvheaderRight = (TextView) findViewById(R.id.tv_common_head_title_right);
        ivheaderRight = (ImageView) findViewById(R.id.iv_common_head_title_right);
        headLine = findViewById(R.id.head_lien);

        //默认隐藏右侧的图片和文字
        tvheaderRight.setVisibility(View.GONE);
        ivheaderRight.setVisibility(View.GONE);

        headBack.setOnClickListener(onClickListener);
        tvheaderRight.setOnClickListener(onClickListener);
        ivheaderRight.setOnClickListener(onClickListener);

    }

    /**
     * 沉浸式处理
     */
    private void initStatusBar() {

        StatusBarUtils.with(this)
                .setColor(getResources().getColor(R.color.color_white))
                .init();
    }

    /**
     * 注册EventBus
     */
    private void registerEventBus() {
        EventBusUtils.register(this);
    }

    /**
     * 取消当前页面的网络请求
     * activity从管理者中删除
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);

        // TODO: 2018/7/12 取消当前页面的网络请求
    }


    //----------- 抽象方法 ---------------

    /**
     * 获取子类的正常view
     */
    protected abstract int getContentView();


    /**
     * 获取数据
     */
    protected abstract void initData();



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void defEventListener(String defData) {

    }


    //    --------- 以下方法供子类使用  ---------

    /**
     * 设置左上角返回图片
     *
     * @param bakImg
     */
    public void setHeadBackImg(int bakImg) {

        headBack.setImageResource(bakImg);
    }

    /**
     * 显示标题栏右侧图片
     */
    public void showHeaderRightImageview(int imaId) {
        ivheaderRight.setVisibility(View.VISIBLE);
        ivheaderRight.setImageResource(imaId);
    }

    /**
     * 显示标题栏右侧文字
     */
    public void showHeaderRightTextview(String headTitle) {
        tvheaderRight.setVisibility(View.VISIBLE);
        tvheaderRight.setText(headTitle);

    }

    /**
     * 隐藏head底部的横线
     */
    public void hideHeadLine() {
        headLine.setVisibility(View.GONE);
    }

    /**
     * 显示正常数据页面
     */
    public void showContentView() {
        statusLayout.showContentView();

    }

    /**
     * 显示空数据页面
     *
     * @param picId           图片id
     * @param title           标题
     * @param emptyButtonText 按钮文字
     *                        传 "" 不显示该控件
     */
    public void showEmptyView(int picId, String title, String emptyButtonText) {
        statusLayout.showEmptyView(picId, title, emptyButtonText);
    }

    /**
     * 显示网络加载失败页面
     *
     * @param picId           图片id
     * @param title           标题
     * @param text            描述
     * @param errorButtonText 按钮文字
     *                        传 "" 不显示该控件
     */
    public void showErrorView(int picId, String title, String text, String errorButtonText) {
        statusLayout.showErrorView(picId, title, text, errorButtonText);

    }

    /**
     * 是否显示标题栏
     *
     * @param show
     */
    public void showHeadLayout(boolean show) {
        headLayout.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    /**
     * 设置窗口透明度
     *
     * @param f
     */
    public void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }


    /**
     * 是否有网络
     */
    public boolean hasNet() {
        return NetUtils.hasNetwork(this);
    }

    /**
     * 获取当前网络状态
     * 0:无网络
     * 1:网络断开或关闭
     * 2:以太网网络
     * 3:wifi网络
     * 4:移动数据连接
     */
    public int getNetStatus() {
        return NetUtils.getNetworkType(this);
    }


    public void showNetTip() {
        int netStatus = getNetStatus();

        String netTip = "";
        switch (netStatus) {
            case 0:
                netTip = "无网络";
                break;

            case 1:
                netTip = "网络断开";
                break;
            case 2:
                netTip = "wifi";
                break;
            case 3:
                netTip = "wifi";
                break;
            case 4:
                netTip = "移动数据";
                break;

        }

        UIHelper.showToast(context, netTip);

    }


    /**
     * 点击事件是否有效，防止重复点击
     */
    public boolean isClickValid() {

        return ClickUtils.isValid();
    }


    //    --------- 以下是空方法 子类选择实现  ---------


    /**
     * 初始化view
     */
    protected void initView() {
    }


    /**
     * 初始化监听器
     */
    protected void initListener() {
    }

    /**
     * 重新加载网页，子类实现
     */
    public void onErrorButtonClick() {
    }

    /**
     * 空白页面的按钮，子类实现
     */
    public void onEmptyButtonClick() {
    }


    /**
     * 标题右边的字点击事件
     */
    protected void onTvRightClick() {
    }

    /**
     * 标题右边的图标点击事件
     */
    protected void onIvRightClick() {
    }


    private StatusLayout.OnStatusLayoutClickListener onStatusLayoutClickListener = new
            StatusLayout.OnStatusLayoutClickListener() {

                @Override
                public void onErrorButtonClick() {
                    BaseActivity.this.onErrorButtonClick();
                }

                @Override
                public void onEmptyButtonClick() {
                    LogUtils.i("onEmptyButtonClick");
                    BaseActivity.this.onEmptyButtonClick();
                }
            };


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_common_head_back:
                    //左上角返回按钮统一处理
                    finish();
                    break;
                case R.id.tv_common_head_title_right:
                    onTvRightClick();
                    break;
                case R.id.iv_common_head_title_right:
                    onIvRightClick();
                    break;

            }
        }
    };
}
~~~


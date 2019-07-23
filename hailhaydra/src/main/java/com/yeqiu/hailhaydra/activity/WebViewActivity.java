package com.yeqiu.hailhaydra.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.app.Url;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/5
 * @describe：
 * @fix：
 */
public class WebViewActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvClose;
    private TextView tvTitle;
    private WebView webView;
    private ProgressBar progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                    .LayoutParams.FLAG_FULLSCREEN);

        }
        setContentView(R.layout.activity_webview);

        init();
    }

    private void init() {
        initView();
        initWebView();
        loadUrl();
        setWebViewClient();
        setWebChromeClient();

    }


    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_web_head_back);
        tvClose = (TextView) findViewById(R.id.tv_web_head_close);
        tvTitle = (TextView) findViewById(R.id.tv_web_head_title);
        progress = (ProgressBar) findViewById(R.id.pb_web_progress);
        webView = (WebView) findViewById(R.id.ev_web);

        tvClose.setVisibility(View.INVISIBLE);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        WebSettings webSettings = webView.getSettings();
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        //持插件
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //设置自适应屏幕
        webSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //缩放操作 是否支持
        webSettings.setSupportZoom(false);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(false);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(true);
        // 用WebView显示图片自适应屏幕，可使用这个参数 设置网页布局类型：1、LayoutAlgorithm.NARROW_COLUMNS适应内容大小
        // 2、LayoutAlgorithm.SINGLE_COLUMN : 适应屏幕，内容将自动缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置WebView是否支持多窗口
        webSettings.setNeedInitialFocus(true);
        //设置DOM Storage缓存
        webSettings.setDomStorageEnabled(true);
        //设置缓存策略
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        //请求焦点
        webView.requestFocusFromTouch();
        // 禁止即在网页顶出现一个空白，又自动回去
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);


    }


    private void setWebViewClient() {


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //当网页开始加载
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //当网页加载完
                if (webView.canGoBack()) {
                    //当前可以返回，显示关闭
                    tvClose.setVisibility(View.VISIBLE);
                } else {
                    tvClose.setVisibility(View.GONE);
                }
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //跳转过程中的url 是否需要拦截

                return super.shouldOverrideUrlLoading(view, url);

            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String
                    failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //当加载错误
            }
        });
    }

    private void setWebChromeClient() {

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //网页加载进度

                if (progress == null) {
                    return;
                }

                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progress.setProgress(newProgress);//设置进度值
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //获取到web标题

                if (!TextUtils.isEmpty(title)) {
                    tvTitle.setText(title);
                }

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
                //js弹出提示框
            }


        });

    }


    protected void loadUrl() {

        webView.loadUrl(Url.jianshu_web);


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK)) {
            handleBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {

        handleBack();

    }


    /**
     * 处理返回键
     */
    private void handleBack() {

        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }


}

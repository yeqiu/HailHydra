package com.yeqiu.hydra.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.UIHelper;

/**
 * @project：XMGJ
 * @author：小卷子
 * @date 2018/10/22
 * @describe：
 * @fix：
 */
public abstract class BaseWebActivity extends BaseActivity {

    protected ProgressBar pbWebProgress;
    protected WebView webView;
    protected String url;
    protected String title;

    @Override
    protected Object getContentView() {

        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {

        pbWebProgress = (ProgressBar) findViewById(R.id.pb_web_progress);
        webView = (WebView) findViewById(R.id.ev_web);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        setHeaderTitle(title);
        initWebView();
        otherSetting();
        loadUrl();
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onBackClick() {
       // super.onBackClick();
        handleBack();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.supportMultipleWindows();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAllowFileAccess(true);
        webSettings.setNeedInitialFocus(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDomStorageEnabled(true);
        webView.requestFocusFromTouch();
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        setWebViewClient(webView);
        setWebChromeClient(webView);

    }


    protected void loadUrl() {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            UIHelper.showToast("加载错误，请退出app后重试");
            finish();
        }

    }


    /**
     * 设置标题
     *
     * @param webTitle
     */
    protected void setWebTitle(String webTitle) {
        if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(webTitle)) {
            //外部没有传入标题

            if (webTitle.length() > 10) {
                webTitle = webTitle.substring(0, 10);
            }

            setHeaderTitle(webTitle);
        }
    }


    public void setWebViewClient(WebView webViewClient) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                String title = view.getTitle();
                setWebTitle(title);
                if (webView != null && webView.canGoBack()) {
                    //当前可以返回，显示关闭
                    tvHeadClose.setVisibility(View.VISIBLE);
                } else {
                    tvHeadClose.setVisibility(View.GONE);
                }

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (webView != null && webView.canGoBack()) {
                    //当前可以返回，显示关闭
                    tvHeadClose.setVisibility(View.VISIBLE);
                } else {
                    tvHeadClose.setVisibility(View.GONE);
                }

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

    }


    private void setWebChromeClient(WebView webView) {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (pbWebProgress == null) {
                    return;
                }

                if (newProgress == 100) {
                    //加载完网页进度条消失
                    pbWebProgress.setVisibility(View.GONE);
                } else {
                    //开始加载网页时显示进度条 设置进度值
                    pbWebProgress.setVisibility(View.VISIBLE);
                    pbWebProgress.setProgress(newProgress);
                }
            }


        });
    }


    protected void handleBack() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    protected abstract void otherSetting();



}

package com.yeqiu.hailhaydra.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.app.Url;
import com.yeqiu.hydra.utils.JumpUtils;

import java.io.ByteArrayOutputStream;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/19
 * @describe：
 * @fix：
 */
public class WebViewScreenShotActivity extends BaseActivity {

    Button btTest;
    WebView wvTest;

    @Override
    protected Object getContentView() {
        return R.layout.activity_webview_screen_shot;
    }

    @Override
    protected void initView() {
        setHeaderTitle(" WebView截长图");



        btTest = (Button) findViewById(R.id.bt_test);
        wvTest = (WebView) findViewById(R.id.wv_test);
        btTest.setOnClickListener(this);

        initWebView();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //关闭weiview的优化，请在初始化view之间执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        WebSettings webSettings = wvTest.getSettings();
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
        wvTest.getSettings().setDomStorageEnabled(true);
        wvTest.requestFocusFromTouch();
        wvTest.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvTest.setWebChromeClient(new WebChromeClient());

        wvTest.loadUrl(Url.ScreenShot);
    }


    /**
     * 5.0以上当前webview截图
     *
     * @param webView
     */
    private void webScreenshotForLollipop(WebView webView) {
        float scale = webView.getScale();
        int width = webView.getWidth();
        int height = (int) (webView.getContentHeight() * scale + 0.5);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        webView.draw(canvas);
        JumpUtils.jumpToActivityByIntent(getIntenWithBitMp(bitmap));
    }


    /**
     * 5.0以下当前webview截图
     *
     * @param webView
     */
    private void webScreenshotForKitKat(WebView webView) {
        Picture picture = webView.capturePicture();
        int width = picture.getWidth();
        int height = picture.getHeight();
        if (width > 0 && height > 0) {
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            picture.draw(canvas);
            JumpUtils.jumpToActivityByIntent(getIntenWithBitMp(bitmap));
        }

    }

    /**
     * 将bitmap转成数组传递到activity，项目中尽量不要使用intent传递bitmap
     *
     * @param bitmap
     * @return
     */
    private Intent getIntenWithBitMp(Bitmap bitmap) {
        Intent intent = new Intent(this, BigPicActivity.class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bitmapByte = baos.toByteArray();
        intent.putExtra("bitmap", bitmapByte);

        return intent;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_test:
                //区分5.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webScreenshotForLollipop(wvTest);
                } else {
                    webScreenshotForKitKat(wvTest);
                }


                break;
        }
    }
}

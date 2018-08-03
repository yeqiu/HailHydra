package com.yeqiu.hailhydra.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.yeqiu.androiddome.R;
import com.yeqiu.hailhydra.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/12
 * @describe：
 * @fix：
 */
public class WebViewScreenshotActivity extends AppCompatActivity {


    @BindView(R.id.bt_test)
    Button btTest;
    @BindView(R.id.iv_test)
    ImageView ivTest;
    @BindView(R.id.wv_test)
    WebView wvTest;

    private String url = "https://www.jianshu.com/p/0faa70e88441";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }

        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);

        initWebView();
    }


    @OnClick(R.id.bt_test)
    public void onViewClicked() {

        // TODO: 2018/8/3  注意申请储存权限

        //区分5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webScreenshotForLollipop(wvTest);
        } else {
            webScreenshotForKitKat(wvTest);
        }



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
        ImageUtils.saveImageToGallery(this, bitmap);
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

            ImageUtils.saveImageToGallery(this, bitmap);
        }

    }


    /**
     * 截取除了导航栏之外的整个屏幕
     */
    private Bitmap screenShotWholeScreen() {
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        return bitmap;

    }

    /**
     * 获取View在屏幕可见区域的截图
     */
    private Bitmap screenShotView(View view) {
        //开启缓存功能
        view.setDrawingCacheEnabled(true);
        //创建缓存
        view.buildDrawingCache();
        //获取缓存Bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        return bitmap;

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

        wvTest.loadUrl(url);
    }


}

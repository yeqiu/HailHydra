package com.yeqiu.hydra.view.activity.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.webkit.WebView;

import com.yeqiu.hydra.utils.JumpUtils;
import com.yeqiu.hydra.view.activity.BaseWebActivity;

import java.io.ByteArrayOutputStream;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/17
 * @describe：
 * @fix：
 */
public class ScreenshotWebActivity extends BaseWebActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //关闭weiview的优化，请在初始化view之间执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void otherSetting() {

        showHeaderRightTextview("截图");
    }


    @Override
    protected void onTvRightClick() {
        super.onTvRightClick();


        getScreenshot();
    }

    private void getScreenshot() {

        //区分5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webScreenshotForLollipop(webView);
        } else {
            webScreenshotForKitKat(webView);
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


}

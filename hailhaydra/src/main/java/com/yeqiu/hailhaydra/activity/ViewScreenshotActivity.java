package com.yeqiu.hailhaydra.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydrautils.utils.JumpUtils;
import com.yeqiu.hydrautils.utils.ScreenUtils;

import java.io.ByteArrayOutputStream;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public class ViewScreenshotActivity extends BaseActivity {

    Button btSsWebview;
    Button btSsScreen;
    Button btSsScreenNoStatusbar;
    Button btSsView;
    Button btSsScrollView;
    private ScrollView slRoot;

    @Override
    protected Object getContentView() {
        return R.layout.activity_view_screen;
    }

    @Override
    protected void initView() {
        setHeaderTitle("View的截图");

        btSsWebview = (Button) findViewById(R.id.bt_ss_webview);
        btSsScreen = (Button) findViewById(R.id.bt_ss_screen);
        btSsScreenNoStatusbar = (Button) findViewById(R.id.bt_ss_screen_no_statusbar);
        btSsView = (Button) findViewById(R.id.bt_ss_view);
        btSsScrollView = (Button) findViewById(R.id.bt_ss_scrollView);
        slRoot = (ScrollView) findViewById(R.id.sl_root);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        btSsWebview.setOnClickListener(this);
        btSsScreen.setOnClickListener(this);
        btSsScreenNoStatusbar.setOnClickListener(this);
        btSsView.setOnClickListener(this);
        btSsScrollView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.bt_ss_webview:

                JumpUtils.jumpToActivityByClass(WebViewScreenShotActivity.class);
                break;
            case R.id.bt_ss_screen:
                //截取的整个屏幕
                Bitmap bitmap = ScreenUtils.screenShotWithStatusBar(ViewScreenshotActivity.this);

                JumpUtils.jumpToActivityByIntent(getIntenWithBitMp(bitmap));

                break;
            case R.id.bt_ss_screen_no_statusbar:
                //截取除了导航栏之外的整个屏幕
                Bitmap bitmapWithoutStatusBar = ScreenUtils.screenShotWithoutStatusBar
                        (ViewScreenshotActivity.this);

                JumpUtils.jumpToActivityByIntent(getIntenWithBitMp(bitmapWithoutStatusBar));

                break;
            case R.id.bt_ss_view:
                //获取View在屏幕可见区域的截图
                Bitmap bitmapView = screenShotView(btSsView);

                JumpUtils.jumpToActivityByIntent(getIntenWithBitMp(bitmapView));
                break;
            case R.id.bt_ss_scrollView:
                //获取ScrollView在屏幕可见区域的截图

                Bitmap scrollViewBitmap = getScrollViewBitmap(slRoot);

                JumpUtils.jumpToActivityByIntent(getIntenWithBitMp(scrollViewBitmap));

                break;
        }
    }


    public static Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }


    /**
     * 获取View在屏幕可见区域的截图 在可视区域外的部分无法截取到
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


    /**
     * 截取scrollview的屏幕
     **/
    public static Bitmap getScrollViewBitmap(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;

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

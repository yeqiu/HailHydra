package com.yeqiu.hailhydra.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.utils.SoftKeyBroadManager;
import com.yeqiu.hailhydra.utils.ToastUtils;


/**
 * @author ye
 * @date 2018/4/2
 * @desc 键盘开启监听
 * https://www.jianshu.com/p/56b91640aa10
 */
public class SoftKeyListenerActivity extends AppCompatActivity {

    private LinearLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softkeykistener);
        root = (LinearLayout) findViewById(R.id.softkeykistener_root);

        initListener();
    }



    private void initListener() {
        SoftKeyBroadManager softKeyBroadManager = new SoftKeyBroadManager(root);
        softKeyBroadManager.addSoftKeyboardStateListener(softKeyboardStateListener);


    }


    private SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new
            SoftKeyBroadManager.SoftKeyboardStateListener() {


                @Override
                public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                    ToastUtils.showToast(getApplicationContext(), "键盘打开");

                }

                @Override
                public void onSoftKeyboardClosed() {
                    ToastUtils.showToast(getApplicationContext(), "键盘关闭");
                }
            };
}

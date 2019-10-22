package com.yeqiu.hydra.view.activity.utils;

import android.view.View;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.KeyBroadListener;
import com.yeqiu.hydra.utils.ToastUtils;
import com.yeqiu.hydra.view.activity.BaseActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public class KeyBordUtilsActivity extends BaseActivity {
    @Override
    protected Object getContentView() {
        return R.layout.activity_keybord_listener;
    }

    @Override
    protected void initView() {
        setHeadTitle("键盘监听");

    }

    @Override
    protected void initData() {

        KeyBroadListener keyBroadListener = new KeyBroadListener(statusLayout);

        keyBroadListener.addKeyboardStateListener(new KeyBroadListener.KeyboardStateListener() {
            @Override
            public void onKeyboardOpened(int keyboardHeightInPx) {
                ToastUtils.showToast("键盘打开");
            }

            @Override
            public void onKeyboardClosed() {
                ToastUtils.showToast("键盘关闭");
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}

package com.yeqiu.hailhaydra.activity;

import android.view.View;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydrautils.utils.KeyBroadListener;
import com.yeqiu.hydrautils.utils.UIHelper;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/15
 * @describe：
 * @fix：
 */
public class KeyBordListenerActivity extends BaseActivity {
    @Override
    protected Object getContentView() {
        return R.layout.activity_keybord_listener;
    }

    @Override
    protected void initView() {
        setHeaderTitle("键盘监听");

    }

    @Override
    protected void initData() {

        KeyBroadListener keyBroadListener = new KeyBroadListener(statusLayout);

        keyBroadListener.addKeyboardStateListener(new KeyBroadListener.KeyboardStateListener() {
            @Override
            public void onKeyboardOpened(int keyboardHeightInPx) {
                UIHelper.showToast("键盘打开");
            }

            @Override
            public void onKeyboardClosed() {
                UIHelper.showToast("键盘关闭");
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

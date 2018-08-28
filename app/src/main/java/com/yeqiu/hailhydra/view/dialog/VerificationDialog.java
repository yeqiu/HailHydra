package com.yeqiu.hailhydra.view.dialog;

import android.app.Activity;
import android.view.View;

import com.yeqiu.hailhydra.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/17
 * @describe：验证码弹框
 * @fix：
 */
public class VerificationDialog extends BaseDialog {


    public VerificationDialog(Activity context) {
        super(context);
    }

    @Override
    public Object getDiaologlayoutIdOrView() {
        return R.layout.layout_verification_dialog;
    }

    @Override
    public void initViewEvent(View view) {

    }
}

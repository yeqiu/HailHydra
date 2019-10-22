package com.yeqiu.hydra.view.activity;

import android.text.TextUtils;

import com.yeqiu.hydra.utils.JumpUtil;
import com.yeqiu.hydra.utils.ToastUtils;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/15
 * @describe：
 * @fix：
 */
public class WebViewActivity extends BaseWebActivity {


    @Override
    protected void otherSetting() {

        if (TextUtils.isEmpty(url)) {
            url = "https://github.com/yeqiu/HailHydra";
            String hint = "url可通过  intent.putExtra(\"url\", url); ";
            ToastUtils.showToast(hint);
        }

        barLayout.setTextViewOnBarRight("浏览器打开");
    }


    @Override
    public void onHeadRightClick(boolean isImg) {
        super.onHeadRightClick(isImg);
        if (!TextUtils.isEmpty(url)) {
            JumpUtil.jumpToBrowserActivity(url);
        }
    }
}

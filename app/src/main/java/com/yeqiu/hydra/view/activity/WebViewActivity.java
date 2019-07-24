package com.yeqiu.hydra.view.activity;

import android.text.TextUtils;

import com.yeqiu.hydra.utils.JumpUtils;
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


        showHeaderRightTextview("浏览器打开");

    }


    @Override
    protected void onTvRightClick() {
        super.onTvRightClick();

        if (!TextUtils.isEmpty(url)) {
            JumpUtils.jumpToBrowserActivity(url);
        }
    }



}

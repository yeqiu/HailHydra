package com.yeqiu.hydra.view.activity;

import com.yeqiu.hydra.utils.UIHelper;

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

        url = "https://github.com/yeqiu/HailHydra";

        String hint = "url可通过  intent.putExtra(\"url\", url); ";
        UIHelper.showToast(hint);
    }
}

package com.yeqiu.hydra.view.activity.demo;

import android.view.View;

import com.yeqiu.docpreview.DocPreview;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.view.activity.BaseActivity;

/**
 * @project：jinjuyunchuang
 * @author：小卷子
 * @date 2019-08-14
 * @describe：
 * @fix：
 */
public class DocPreviewActivity extends BaseActivity {

    private DocPreview dpv;

    @Override
    protected Object getContentView() {
        return R.layout.activity_doc_preview;
    }

    @Override
    protected void initView() {

        setHeaderTitle("查看文档");
        dpv = (DocPreview) findViewById(R.id.dpv);
    }

    @Override
    protected void initData() {

        String url = "/storage/emulated/0/zhihu/test.pdf";

        dpv.openFile(this, url);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dpv != null) {
            dpv.onDestroy();
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View view) {

    }
}

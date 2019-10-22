package com.yeqiu.hydra.view.activity.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import com.yeqiu.docpreview.DocPreview;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.FileProviderUtils;
import com.yeqiu.hydra.view.activity.BaseActivity;

import java.io.File;

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

        setHeadTitle("查看文档");
        dpv = (DocPreview) findViewById(R.id.dpv);
    }

    @Override
    protected void initData() {

        String url = "/storage/emulated/0/zhihu/test.pdf";

        boolean canOpen = dpv.openFile(this, url);

        if (!canOpen) {
            openPdfByOtherApp(url);
        }


    }


    private void openPdfByOtherApp(String path) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Uri uri = FileProviderUtils.getUriForFile(path);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/pdf");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(path)), "application/pdf");
        }
        startActivity(intent);
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

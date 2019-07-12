package com.yeqiu.hailhaydra.activity;

import android.view.View;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.utils.UpdateUtil;
import com.yeqiu.hydra.view.dialog.CommonDialog;
import com.yeqiu.hydra.view.dialog.callback.DialogListener;

import java.io.File;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/17
 * @describe：
 * @fix：
 */
public class UpdateActivity extends BaseActivity {

    private TextView tvResult;

    @Override
    protected Object getContentView() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {
        setHeaderTitle("app升级和安装");

        tvResult = (TextView) findViewById(R.id.tv_update_result);
    }

    @Override
    protected void initData() {

        new CommonDialog(UpdateActivity.this)
                .build()
                .setTitleText("更新")
                .setDescText("有新版本")
                .setOnDialogListener(new DialogListener() {
                    @Override
                    public void onConfirmClick() {
                        super.onConfirmClick();
                        update();
                    }
                })
                .show();


    }


    private void update() {

        UpdateUtil updateUtil = new UpdateUtil();
        String s = "https://imtt.dd.qq.com/16891/FE7240E456F9594D2A534E2F2B86C595.apk?fsname=com" +
                ".zhiqupk.ziti_6.2.6_6002006.apk&csr=1bbd";
        updateUtil.update(s, "1.1");
        updateUtil.setOnDownloadProgress(new UpdateUtil.OnDownloadProgress() {
            @Override
            public void onSuccess(File file) {
                tvResult.setText("下载成功，文件地址 ：" + file.getAbsolutePath());
            }

            @Override
            public void onError() {

            }

            @Override
            public void onDownloadProgress(int progress) {

                tvResult.setText(progress + "%");
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

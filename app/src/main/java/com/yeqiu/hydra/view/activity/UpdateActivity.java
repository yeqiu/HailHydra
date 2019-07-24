package com.yeqiu.hydra.view.activity;

import android.Manifest;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.yanzhenjie.permission.AndPermission;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.utils.APPInfoUtil;
import com.yeqiu.hydra.utils.ToastUtils;
import com.yeqiu.hydra.utils.UpdateUtil;
import com.yeqiu.hydra.view.dialog.CommonDialog;
import com.yeqiu.hydra.view.dialog.callback.DialogListener;

import java.io.File;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/10
 * @describe：
 * @fix：
 */
public class UpdateActivity extends BaseActivity {

    private TextView tvUpdate;
    private NumberProgressBar npbUpdate;
    private UpdateUtil updateUtil;

    @Override
    protected Object getContentView() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {
        setHeaderTitle("检查升级");
        tvUpdate = (TextView) findViewById(R.id.tv_update);
        npbUpdate = (NumberProgressBar) findViewById(R.id.npb_update);
    }

    @Override
    protected void initData() {


        String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .start();

    }

    @Override
    protected void initListener() {
        tvUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_update:
                showDialog();
                break;

            default:
                break;
        }
    }

    private void showDialog() {

        new CommonDialog(UpdateActivity.this)
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


        boolean notificationOpen = APPInfoUtil.isNotificationOpen();
        if (!notificationOpen){
            ToastUtils.showToast("请在设置中开启通知权限");
        }


        updateUtil = new UpdateUtil();
        String s = "https://imtt.dd.qq.com/16891/FE7240E456F9594D2A534E2F2B86C595.apk?fsname=com" +
                ".zhiqupk.ziti_6.2.6_6002006.apk&csr=1bbd";
        //icon必须为小尺寸，40*40甚至32*32为佳 否则无效
        updateUtil.openNotification(R.drawable.icon_head_hydra_1);
        updateUtil.update(s, "1.1");
        updateUtil.setOnDownloadProgress(new UpdateUtil.OnDownloadProgress() {
            @Override
            public void onSuccess(File file) {
                tvUpdate.setText("下载成功，文件地址 ：" + file.getAbsolutePath());
            }

            @Override
            public void onError() {

            }

            @Override
            public void onDownloadProgress(int progress) {

                npbUpdate.setProgress(progress);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();{
            if (updateUtil!=null){
                updateUtil.cancel();
            }
        }
    }
}

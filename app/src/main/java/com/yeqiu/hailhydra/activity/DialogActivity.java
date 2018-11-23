package com.yeqiu.hailhydra.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.utils.UIHelper;
import com.yeqiu.hydrautils.view.dialog.CommonDialog;
import com.yeqiu.hydrautils.view.dialog.DialogListener;
import com.yeqiu.hydrautils.view.dialog.EditDialog;
import com.yeqiu.hydrautils.view.dialog.SheetDialog;
import com.yeqiu.hydrautils.view.dialog.TipDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/6/1
 * @describe：
 * @fix：
 */
public class DialogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();


    }

    private void initView() {
        Button CommonDialog_1 = (Button) findViewById(R.id.CommonDialog_1);
        Button CommonDialog_2 = (Button) findViewById(R.id.CommonDialog_2);
        Button EditDialog = (Button) findViewById(R.id.EditDialog);
        Button SheetDialog = (Button) findViewById(R.id.SheetDialog);
        Button tipDialog = (Button) findViewById(R.id.tipDialog);

        CommonDialog_1.setOnClickListener(onClickListener);
        CommonDialog_2.setOnClickListener(onClickListener);
        EditDialog.setOnClickListener(onClickListener);
        SheetDialog.setOnClickListener(onClickListener);
        tipDialog.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.CommonDialog_1:

                    new CommonDialog(DialogActivity.this)
                            .build()
                            .setTitleText("测试标题")
                            .setDescText("测试内容")
                            .setOnDialogListener(new DialogListener() {
                                @Override
                                public void onConfirmClick() {
                                    super.onConfirmClick();
                                    UIHelper.showToast(getApplicationContext(), "确定");
                                }

                                @Override
                                public void onCanceclClick() {
                                    super.onCanceclClick();
                                    UIHelper.showToast(getApplicationContext(), "取消");
                                }
                            })
                            .show();


                    break;

                case R.id.CommonDialog_2:


                    new CommonDialog(DialogActivity.this)
                            .build()
                            .setTitleText("测试标题")
                            .setDescText("测试内容")
                            .setJustConfirm(true)
                            .setOnDialogListener(new DialogListener() {
                                @Override
                                public void onConfirmClick() {
                                    super.onConfirmClick();
                                    UIHelper.showToast(getApplicationContext(), "确定");
                                }

                            })
                            .show();

                    break;

                case R.id.EditDialog:
                    new EditDialog(DialogActivity.this)
                            .build()
                            .setTitleText("测试标题")
                            .setDescText("测试内容")
                            .setOnDialogListener(new DialogListener() {
                                @Override
                                public void onConfirmClick(String inputText) {
                                    super.onConfirmClick();
                                    UIHelper.showToast(getApplicationContext(), inputText);
                                }

                                @Override
                                public void onCanceclClick(String inputText) {
                                    super.onCanceclClick();
                                    UIHelper.showToast(getApplicationContext(), inputText);
                                }

                            })
                            .show();


                    break;

                case R.id.SheetDialog:

                    List<String> items = new ArrayList<>();
                    items.add("我走中路");
                    items.add("我打野");
                    items.add("我打ADC");

                    new SheetDialog(DialogActivity.this)
                            .build()
                            .setSheetDatas(items)
                            .setTitleText("标题")
                            .setOnDialogListener(new DialogListener() {
                                @Override
                                public void onItemClick(int position, String text) {
                                    UIHelper.showToast(getApplicationContext(), text);
                                }
                            })
                            .show();


                    break;


                case R.id.tipDialog:

//
//                    tipDialog.show();
//
//                    tipDialog.setDismissTime(1500);


                    new TipDialog(DialogActivity.this)
                            .build()
                            .setIconId(R.drawable.icon_done)
                            .setTipText("test")
                            .setOrientationHorizontal(true)
                            .show();



                    break;


                default:
                    break;


            }

        }
    };
}

package com.yeqiu.androidlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yeqiu.androidlibrary.view.dialog.CommonDialog;
import com.yeqiu.androidlibrary.view.dialog.EditDialog;
import com.yeqiu.androidlibrary.view.dialog.SheetDialog;
import com.yeqiu.androidlibrary.view.dialog.TipDialog;
import com.yeqiu.androiddome.R;

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

    private TipDialog tipDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();

        tipDialog = new TipDialog.Builder(DialogActivity.this)
                .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();

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
                    CommonDialog.build(DialogActivity.this)
                            .setTitleText("标题")
                            .setContentText("内容")
                            .show();
                    break;

                case R.id.CommonDialog_2:
                    CommonDialog.build(DialogActivity.this)
                            .setTitleText("标题")
                            .setContentText("内容")
                            .setJustConfirm(true)
                            .show();
                    break;

                case R.id.EditDialog:
                    EditDialog.build(DialogActivity.this)
                            .setTitleText("标题")
                            .setHint("提示内容")
                            .setDialogCallBack(new EditDialog.EditDialogClickListener() {
                                @Override
                                public void onConfirmClick(String inputText) {
                                    Toast.makeText(getApplicationContext(), inputText,
                                            Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCanceclClick(String inputText) {

                                }
                            })
                            .show();
                    break;

                case R.id.SheetDialog:

                    final List<String> items = new ArrayList<>();
                    items.add("我走中路");
                    items.add("我打野");
                    items.add("我打ADC");


                    SheetDialog.build(DialogActivity.this)
                            .setTitleText("标题")
                            .setItem(items)
                            .setItemColor(R.color.color_0d95ff)
                            .setDialogClickListener(new SheetDialog.DialogClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Toast.makeText(getApplicationContext(), items.get(position),
                                            Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCanceclClick() {

                                }
                            })
                            .show();
                    break;


                case R.id.tipDialog:


                    tipDialog.show();



                    break;


            }

        }
    };
}

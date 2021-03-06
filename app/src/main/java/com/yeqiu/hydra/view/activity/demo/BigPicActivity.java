package com.yeqiu.hydra.view.activity.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.view.activity.BaseActivity;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/19
 * @describe：
 * @fix：
 */
public class BigPicActivity extends BaseActivity {

    private ImageView pic;

    @Override
    protected Object getContentView() {
        return R.layout.activity_big_pic;
    }

    @Override
    protected void initView() {
        showHeadBar(false);

        pic = (ImageView) findViewById(R.id.iv_big_pic);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        byte [] bis=intent.getByteArrayExtra("bitmap");
        if (bis!=null){
            Bitmap bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            pic.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}

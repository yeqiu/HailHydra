package com.yeqiu.hailhaydra.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.yeqiu.hailhaydra.R;

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
        showHeadLayout(false);

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
    public void onClick(View v) {

    }
}

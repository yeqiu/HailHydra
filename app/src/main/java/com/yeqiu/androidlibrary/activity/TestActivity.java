package com.yeqiu.androidlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yeqiu.androiddome.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/12
 * @describe：
 * @fix：
 */
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.tv_test)
    Button tvTest;
    @BindView(R.id.iv_test)
    ImageView ivTest;


    private String url = "http://ww1.sinaimg.cn/large/0065oQSqly1fswhaqvnobj30sg14hka0.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.tv_test)
    public void onViewClicked() {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.sishen)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(this)
                .load(url)
                .apply(options)
                .into(ivTest);

    }
}

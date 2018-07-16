package com.yeqiu.androidlibrary.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.yeqiu.androiddome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/12
 * @describe：
 * @fix：
 */
public class TestActivity extends AppCompatActivity {


    @BindView(R.id.et_test)
    EditText etTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);


    }


}

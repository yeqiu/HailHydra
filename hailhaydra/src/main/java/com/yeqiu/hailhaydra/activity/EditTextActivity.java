package com.yeqiu.hailhaydra.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.widget.EditText;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.Test.DecimalDigitsInputFilter;


/**
 * @author ye
 * @date 2018/4/11
 * @desc
 */
public class EditTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        EditText viewById = (EditText) findViewById(R.id.test);

        viewById.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(10);
        viewById.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2), lengthFilter});
    }
}

package com.yeqiu.hailhaydra.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputFilter;
import android.widget.EditText;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.utils.DecimalDigitsInputFilter;


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

package com.yeqiu.hailhaydra.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yeqiu.hailhaydra.R;

import java.lang.reflect.InvocationTargetException;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/15
 * @describe：
 * @fix：
 */
public class TestOne extends AppCompatActivity {

    ImageView ivTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }

    private void onInitView() {
        ivTest = (ImageView) findViewById(R.id.iv_test);


        Class<? extends ViewGroup.LayoutParams> LayoutParamsClass = ivTest.getLayoutParams()
                .getClass();

        try {

            ViewGroup.LayoutParams layoutParams = LayoutParamsClass.getDeclaredConstructor(int
                    .class, int.class)
                    .newInstance(1080, 1080);


            ivTest.setLayoutParams(layoutParams);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}

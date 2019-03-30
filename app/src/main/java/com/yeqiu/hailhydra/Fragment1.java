package com.yeqiu.hailhydra;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @project：map
 * @author：小卷子
 * @date 2019/3/19
 * @describe：
 * @fix：
 */
public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.activity_test, null);

        TextView tv = (TextView) inflate.findViewById(R.id.tv);

        tv.setText("1");

        return inflate;
    }
}

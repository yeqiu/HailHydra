package com.yeqiu.hailhaydra.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.adapter.CheckboxListAdapter;
import com.yeqiu.hailhaydra.bean.Model.CheckboxData;
import com.yeqiu.hailhaydra.callback.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/15
 * @describe：
 * @fix：
 */
public class CheckboxListActivity extends BaseActivity {


    ListView lvCheckboxList;
    CheckBox cbCheckboxList;
    TextView tvCheckboxList;

    private List<CheckboxData> datas = new ArrayList<>();
    /**
     * 总金额
     */
    private int sum = 0;
    /**
     * 选中的数量，用来判断是否是全选
     */
    private int checkCount = 0;
    private CheckboxListAdapter checkboxListAdapter;

    @Override
    protected Object getContentView() {
        return R.layout.activity_checkbox_list;
    }

    @Override
    protected void initView() {

        setHeaderTitle("ListView中使用Checkbox");

        lvCheckboxList = (ListView) findViewById(R.id.lv_checkbox_list);
        cbCheckboxList = (CheckBox) findViewById(R.id.cb_checkbox_list);
        tvCheckboxList = (TextView) findViewById(R.id.tv_checkbox_list);

    }

    @Override
    protected void initData() {


        for (int i = 1; i < 21; i++) {
            CheckboxData checkboxData = new CheckboxData();
            checkboxData.setName("快乐上单狗 " + i);
            checkboxData.setMoney(1);
            checkboxData.setCheck(false);
            datas.add(checkboxData);
        }


        checkboxListAdapter = new CheckboxListAdapter(datas);

        lvCheckboxList.setAdapter(checkboxListAdapter);


        checkboxListAdapter.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(int position, boolean isChecked, CheckboxData
                    checkboxData) {

                //isChecked代表item中CheckedBox修改后的状态，
                if (isChecked) {
                    checkCount++;
                    sum = sum + checkboxData.getMoney();
                } else {
                    checkCount--;
                    sum = sum - checkboxData.getMoney();
                }

                //区分是否全选
                if (checkCount >= datas.size()) {
                    cbCheckboxList.setChecked(true);
                } else {
                    cbCheckboxList.setChecked(false);
                }

                tvCheckboxList.setText("总计" + sum);

            }
        });

    }

    @Override
    protected void initListener() {

        //全选的监听
        cbCheckboxList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!buttonView.isPressed()) {
                    return;
                }

                //listview中所有的item统一设置
                if (checkboxListAdapter != null) {
                    checkboxListAdapter.setAllCheck(isChecked);
                }

                //设置全选或者全不选
                if (isChecked) {
                    checkCount = datas.size();

                    //总金额置0 重新计算总额
                    sum = 0;
                    for (int i = 0; i < datas.size(); i++) {
                        sum = sum + datas.get(i).getMoney();
                    }
                } else {
                    sum = 0;
                    checkCount = 0;
                }

                tvCheckboxList.setText("总计：" + sum);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}

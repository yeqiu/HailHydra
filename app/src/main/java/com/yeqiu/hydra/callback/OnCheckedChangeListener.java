package com.yeqiu.hydra.callback;


import com.yeqiu.hydra.bean.Model.CheckboxData;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/15
 * @describe：
 * @fix：
 */
public interface OnCheckedChangeListener {

    void onCheckedChange(int position, boolean isChecked, CheckboxData checkboxData);
}

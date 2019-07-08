package com.yeqiu.hydra.bean.Model;

import java.io.Serializable;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/15
 * @describe：
 * @fix：
 */
public class CheckboxData implements Serializable {


    private String name;
    private int money;
    private boolean isCheck;


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}

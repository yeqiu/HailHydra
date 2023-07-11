package com.yeqiu.easyandroid

import android.content.Context
import android.widget.Toast

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/9
 * @describe：
 * @fix：
 */

fun Context.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
package com.yeqiu.fastmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/9
 * @describe：
 * @fix：
 */
open class BaseViewModel : ViewModel() {

     val loadingStatus: OnLoadingEvent by lazy { OnLoadingEvent() }


    inner class OnLoadingEvent {
        //显示加载框
        val show by lazy { MutableLiveData<String>() }

        //隐藏
        val dismiss by lazy { MutableLiveData<Boolean>() }
    }
}
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

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套才加的，不然我加他个鸡儿加
     */
    inner class OnLoadingEvent {
        //显示加载框
        val show by lazy { MutableLiveData<String>() }

        //隐藏
        val dismiss by lazy { MutableLiveData<Boolean>() }
    }
}
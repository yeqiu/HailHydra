package com.yeqiu.fastmvvm.ext.me.hgj.jetpackmvvm

import androidx.lifecycle.viewModelScope
import com.yeqiu.easyandroid.log
import com.yeqiu.fastmvvm.exception.NetWorkException
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/10
 * @describe：
 * @fix：
 */


fun <T> BaseViewModel.sendRequest(
    block: suspend () -> T ,
    success: (T) -> Unit,
    error: (NetWorkException) -> Unit = {},
    isShowDialog: Boolean = true,
    loadingMessage: String = "请求网络中..."
): Job {

    return viewModelScope.launch {
        runCatching {
            if (isShowDialog){
                loadingStatus.show.postValue(loadingMessage)
            }
            block.invoke()
        }.onSuccess {
            loadingStatus.dismiss.postValue(false)
            runCatching {
                //todo 校验结果是否正确
                "校验结果".log()
                success.invoke(it)
            }.onFailure {
                //todo 构建错误信息
                error.invoke(NetWorkException(it))
            }
        }.onFailure {
            //网络请求错误
            loadingStatus.dismiss.postValue(false)
            //todo 构建错误信息
            error.invoke(NetWorkException(it))
        }
    }
}



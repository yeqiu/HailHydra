package com.yeqiu.fastmvvm.ext

import androidx.lifecycle.viewModelScope
import com.yeqiu.fastmvvm.exception.NetworkErrorCode
import com.yeqiu.fastmvvm.exception.NetworkException
import com.yeqiu.fastmvvm.exception.checkResponseErrorCode
import com.yeqiu.fastmvvm.exception.unknownErrorCode
import com.yeqiu.fastmvvm.network.NetworkConfig
import com.yeqiu.fastmvvm.network.NetworkResponse
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/10
 * @describe：
 * @fix：
 */

/**
 * 数据自动脱壳
 * @receiver BaseViewModel
 * @param block 执行网络请求
 * @param success 成功回调
 * @param error 失败回调
 * @param isShowDialog Boolean
 * @param loadingMessage String
 * @return Job
 */
fun <T> BaseViewModel.requestData(
    block: suspend () -> NetworkResponse<T>,
    success: (T?) -> Unit,
    error: (NetworkException) -> Unit = NetworkConfig.onNetworkError,
    isShowDialog: Boolean = true,
    loadingMessage: String = "请求网络中..."
): Job {
    return viewModelScope.launch {
        runCatching {
            //校验网络是否连接

            if (isShowDialog) {
                loadingStatus.show.postValue(loadingMessage)
            }
            withContext(Dispatchers.IO) {
                block.invoke()
            }
        }.onSuccess {
            loadingStatus.dismiss.postValue(false)
            runCatching {
                checkResponse(it) {
                    success.invoke(it)
                }
            }.onFailure {
                if (it is NetworkException) {
                    //脱壳校验时错误
                    error.invoke(
                        NetworkException(
                            errorCode = checkResponseErrorCode,
                            errorMessage = it.errorMessage,
                            throwable = it
                        )
                    )
                } else {
                    error.invoke(
                        NetworkException(
                            errorCode = unknownErrorCode,
                            errorMessage = it.message ?: "未知错误",
                            throwable = it
                        )
                    )
                }
            }
        }.onFailure {
            it.printStackTrace()
            //网络请求错误
            loadingStatus.dismiss.postValue(false)
            error.invoke(
                NetworkException(
                    errorCode = NetworkErrorCode,
                    errorMessage = it.message?:"网络请求错误",
                    throwable = it
                )
            )
        }
    }
}


fun <T> BaseViewModel.requestOriginalData(
    block: suspend () -> T,
    success: (T?) -> Unit,
    error: (NetworkException) -> Unit = NetworkConfig.onNetworkError,
    isShowDialog: Boolean = true,
    loadingMessage: String = "请求网络中..."
): Job {

    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) {
                loadingStatus.show.postValue(loadingMessage)
            }
            block.invoke()
        }.onSuccess {
            loadingStatus.dismiss.postValue(false)
            success.invoke(it)
        }.onFailure {
            it.printStackTrace()
            //网络请求错误
            loadingStatus.dismiss.postValue(false)
            // 网络请求错误
            // TODO: 是否需要定义错误的code和msg
            error.invoke(NetworkException(throwable = it))
        }
    }
}


suspend fun <T> checkResponse(
    response: NetworkResponse<T>,
    success: (T?) -> Unit
) {

    coroutineScope {
        if (response.isSuccess()) {
            success.invoke(response.getResponse())
        } else {
            throw NetworkException(
                response.getCode(),
                response.getErrorMessage()
            )
        }

    }

}



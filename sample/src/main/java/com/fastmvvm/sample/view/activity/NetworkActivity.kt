package com.fastmvvm.sample.view.activity

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.fastmvvm.sample.databinding.ActivityNetWorkBinding
import com.fastmvvm.sample.network.NetworkClient
import com.google.gson.Gson
import com.yeqiu.easyandroid.log
import com.yeqiu.easyandroid.showToast
import com.yeqiu.fastmvvm.ext.requestData
import com.yeqiu.fastmvvm.ext.requestOriginalData
import com.yeqiu.fastmvvm.network.NetworkConfig
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

class NetworkActivity : BaseActivity<NetWorkViewModel, ActivityNetWorkBinding>() {

    override fun initData(savedInstanceState: Bundle?) {

        binding.viewModle = viewModel
        //默认错误处理
        NetworkConfig.onNetworkError = { it ->
            showToast("默认错误处理,错误信息 = ${it.errorMessage}")
        }

    }


    override fun addObserve() {

    }

}


fun Any.toJson(): String {


    return Gson().toJson(this)

}

class NetWorkViewModel : BaseViewModel() {

    val data = MutableLiveData<String>()


    fun shuck() {

        requestData(
            {
                NetworkClient.api.banner2()
            },
            {
                data.value = it?.toJson()
                it?.toJson()?.log()
            }
        )
    }

    fun originalData() {

        requestOriginalData(
            block = {
                NetworkClient.api.banner()
            },
            success = {
                data.value = it?.toJson()
                it?.toJson()?.log()
            }
        )
    }

    fun error() {

        requestData(
            block = {
                NetworkClient.api.login("aa","")
            },
            success = {}
        )

    }

    fun handleError() {
        requestData(
            block = {
                delay(2000)
                throw IllegalArgumentException()
                NetworkClient.api.banner2()
            },
            success = {},
            error = {
                data.value = "这个一个自定义处理错误"
            }
        )
    }

    fun withOutLoading() {
        requestData(
            {
                NetworkClient.api.banner2()
            },
            {
                data.value = it?.toJson()
                it?.toJson()?.log()
            },
            isShowDialog = false
        )
    }

    fun withStatus() {

    }


}
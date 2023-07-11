package com.fastmvvm.sample.view.activity

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.fastmvvm.sample.databinding.ActivityMainBinding
import com.fastmvvm.sample.network.NetworkClient
import com.fastmvvm.sample.network.User
import com.yeqiu.easyandroid.showToast
import com.yeqiu.fastmvvm.ext.me.hgj.jetpackmvvm.sendRequest
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.delay


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


    override fun initData(savedInstanceState: Bundle?) {
        binding.mainViewModel = viewModel

    }

    override fun addObserve() {

        viewModel.errorMsg.observe(this) {
           showToast(it)
        }

    }


}

class MainViewModel : BaseViewModel() {

    val user = MutableLiveData<User>()
    val errorMsg = MutableLiveData<String>()

    fun getData() {

        sendRequest(
            block = { NetworkClient.api.getUser("yeqiu") },
            success = { user.value = it },
        )
    }

    fun testError() {

        sendRequest(
            block = {
                delay(2000)
                throw IllegalArgumentException("模拟错误")
                NetworkClient.api.getUser("yeqiu")
                    },
            success = { user.value = it },
            error = {
                errorMsg.value = "这是一个模拟错误"
            }
        )
    }

}
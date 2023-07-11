package com.fastmvvm.sample

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.yeqiu.easyandroid.log
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel


/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/10
 * @describe：
 * @fix：
 */
class App : Application(), ViewModelStoreOwner {

    companion object {
        lateinit var instance: App
        lateinit var appViewModel: AppViewModel
    }

    override fun getViewModelStore(): ViewModelStore {
        return ViewModelStore()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        createAppViewModel()
    }

    private fun createAppViewModel() {
        "createAppViewModel" .log()
        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
    }



}

class AppViewModel : BaseViewModel() {

    val data =MutableLiveData("data")

}
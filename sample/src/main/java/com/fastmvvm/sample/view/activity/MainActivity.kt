package com.fastmvvm.sample.view.activity

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fastmvvm.sample.databinding.ActivityMainBinding
import com.yeqiu.easyandroid.toActivity
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun initData(savedInstanceState: Bundle?) {
        binding.viewModle = viewModel
    }

    override fun addObserve() {

        viewModel.detail.observe(this) {

            if (it) {
                toActivity<DetailActivity>()
            }

        }
        viewModel.login.observe(this) {
            if (it) {
                toActivity<LoginActivity>()
            }
        }
        viewModel.singleActivity.observe(this) {
            if (it) {
                toActivity<BottomNavigationAcitvity>()
            }
        }
        viewModel.netWork.observe(this) {

            if (it) {
                toActivity<NetworkActivity>()
            }

        }


    }

}

class MainViewModel : BaseViewModel() {

    val detail = MutableLiveData<Boolean>(false)
    val login = MutableLiveData<Boolean>(false)
    val singleActivity = MutableLiveData<Boolean>(false)
    val netWork = MutableLiveData<Boolean>(false)


    fun clickDetail() {
        detail.value = true
    }

    fun clickLogin() {
        login.value = true
    }

    fun clickSingleActivity() {
        singleActivity.value = true
    }

    fun clickNetwork() {
        netWork.value = true
    }
   fun clickLoading() {

       //三秒后自动关闭
       viewModelScope.launch {
           loadingStatus.show.postValue("加载。。。")
           delay(3000)
           loadingStatus.dismiss.postValue(true)
       }
    }


}
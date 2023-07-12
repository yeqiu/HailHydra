package com.fastmvvm.sample.view.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.fastmvvm.sample.databinding.ActivityDetailBinding
import com.fastmvvm.sample.network.NetworkClient
import com.fastmvvm.sample.network.User
import com.yeqiu.easyandroid.showToast
import com.yeqiu.fastmvvm.ext.requestOriginalData
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.delay


class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {


    override fun initData(savedInstanceState: Bundle?) {
        binding.mainViewModel = viewModel

        viewModel.getData()

    }

    override fun addObserve() {

        viewModel.errorMsg.observe(this) {
            showToast(it)
        }

    }


}


class ImageLoader {
    companion object {
        @JvmStatic
        @BindingAdapter("url")
        fun loadUrl(imageView: ImageView, url: String?) {
            url?.let {
                Glide.with(imageView.context)
                    .load(it)
                    .into(imageView)
            }


        }
    }
}


class DetailViewModel : BaseViewModel() {

    val user = MutableLiveData<User>()
    val errorMsg = MutableLiveData<String>()

    fun getData() {

        requestOriginalData(
            {
                delay(2000)
                NetworkClient.api.getUser("https://api.github.com/users/yeqiu")
            },
            { user.value = it }
        )


    }

    fun testError() {


    }

}
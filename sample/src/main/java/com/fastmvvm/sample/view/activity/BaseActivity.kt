package com.fastmvvm.sample.view.activity

import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.fastmvvm.sample.R
import com.yeqiu.fastmvvm.view.ViewModelDataBindingActivity
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/9
 * @describe：
 * @fix：
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : ViewModelDataBindingActivity<VM,VB>() {


    private lateinit var loadingDialog: MaterialDialog


    override fun showLoading(message:String) {


        if (!::loadingDialog.isInitialized) {
            loadingDialog = MaterialDialog(this)
                .cancelable(true)
                .cancelOnTouchOutside(false)
                .cornerRadius(12f)
                .customView(R.layout.dialog_loading)
                .lifecycleOwner(this)
            loadingDialog.getCustomView().run {
                (findViewById<TextView>(R.id.loading_tips)).text =message
            }
        }

        loadingDialog.show()
    }

    override fun dismissLoading() {

        if (::loadingDialog.isInitialized){
            loadingDialog.dismiss()
        }

    }
}
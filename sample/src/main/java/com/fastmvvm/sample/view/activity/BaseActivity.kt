package com.fastmvvm.sample.view.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
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


    private lateinit var loadingDialog: Dialog


    override fun showLoading() {
        if (!::loadingDialog.isInitialized) {
            loadingDialog = Dialog(this)
            loadingDialog.apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(ProgressBar(context).apply {
                    isIndeterminate = true
                    indeterminateDrawable.setColorFilter(
                        ContextCompat.getColor(context, R.color.purple_200),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                })
                window?.apply {
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setDimAmount(0.3f)
                    setGravity(Gravity.CENTER)
                    setFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    )
                }
                setCancelable(false)
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
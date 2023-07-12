package com.yeqiu.fastmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.yeqiu.fastmvvm.exception.GenerateViewDataBindingException
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
abstract class ViewModelDataBindingActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {


    protected lateinit var viewModel: VM
    protected lateinit var binding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = generateViewDataBinding()
        setContentView(binding.root)
        //绑定lifecycleOwner
        binding.lifecycleOwner =this
        init()
        initData(savedInstanceState)
    }




    private fun init() {
        viewModel = createViewModel()
        registerLoading()
        addObserve()
    }


    @Suppress("UNCHECKED_CAST")
    private fun generateViewDataBinding(): VB {

        try {
            val clazz =
                (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
            val inflate = clazz.getMethod("inflate", LayoutInflater::class.java)
            return inflate.invoke(null, layoutInflater) as VB
        } catch (e: Exception) {
            e.printStackTrace()
            throw GenerateViewDataBindingException("fail to generate ViewDataBinding cause ${e.message} ")
        }
    }



    private fun createViewModel(): VM {

        return ViewModelProvider(this)[getViewModelClazz(this)]
    }

    private fun registerLoading() {

        viewModel.loadingStatus.show.observe(this) {
            showLoading(it)
        }
        viewModel.loadingStatus.dismiss.observe(this) {
            dismissLoading()
        }

    }




    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun addObserve()

    abstract fun showLoading(message:String)

    abstract fun dismissLoading()



}
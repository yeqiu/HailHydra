package com.yeqiu.fastmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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

abstract class ViewModelDataBindingFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = generateViewDataBinding(container!!)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initData(savedInstanceState)
    }

    private fun init() {
        viewModel = createViewModel()
        registerLoading()
        addObserve()



    }


    @Suppress("UNCHECKED_CAST")
    private fun generateViewDataBinding(container: ViewGroup): VB {

        try {
            val clazz =
                (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
            val inflate = clazz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            return inflate.invoke(null, layoutInflater, container, false) as VB

        } catch (e: Exception) {
            e.printStackTrace()
            throw GenerateViewDataBindingException("fail to generate ViewDataBinding cause ${e.message} ")
        }
    }

    private fun createViewModel(): VM {

        return ViewModelProvider(this)[getViewModelClazz(this)]
    }

    private fun registerLoading() {


        viewModel.loadingStatus.show.observe(viewLifecycleOwner){
            showLoading()
        }
        viewModel.loadingStatus.dismiss.observe(viewLifecycleOwner) {
            dismissLoading()
        }

    }



    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun addObserve()

    abstract fun showLoading()

    abstract fun dismissLoading()

}


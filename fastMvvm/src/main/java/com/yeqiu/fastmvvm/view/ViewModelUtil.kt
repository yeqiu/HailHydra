package com.yeqiu.fastmvvm.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yeqiu.fastmvvm.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType


/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getViewModelClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}



/**
 * 在Activity中得到Application上下文的ViewModel
 */
inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
   TODO()
}

/**
 * 在Fragment中得到Application上下文的ViewModel
 * 提示，在fragment中调用该方法时，请在该Fragment onCreate以后调用或者请用by lazy方式懒加载初始化调用，不然会提示requireActivity没有导致错误
 */
inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
    TODO()
}











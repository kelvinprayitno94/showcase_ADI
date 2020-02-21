package com.hino.hearts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hino.hearts.R
import kotlinx.android.synthetic.main.main_toolbar.*

abstract class BaseActivity<U : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: U

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        actionBar?.setHomeAsUpIndicator(R.drawable.back)
//        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setBinding(layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding.lifecycleOwner = this
    }

    abstract fun initObserver()

    abstract fun initViewModel()

    abstract fun initEvent()
}
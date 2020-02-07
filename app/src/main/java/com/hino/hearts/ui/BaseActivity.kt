package com.hino.hearts.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<U : ViewDataBinding> : AppCompatActivity() {

    private lateinit var binding: U

    fun setBinding(layoutResID: Int) {
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding.lifecycleOwner = this
    }
}
package com.hino.hearts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Dihardja Software on 2020-02-07.
 */
abstract class BaseFragment<U : ViewDataBinding> : Fragment() {

    protected lateinit var binding: U

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    abstract fun getLayoutId():Int

    abstract fun initObserver()

    abstract fun initViewModel()

    abstract fun initEvent()
}
package com.hino.hearts.util.module

import com.hino.hearts.ui.home.HomeFragmentViewModel
import com.hino.hearts.ui.home.HomeViewModel
import com.hino.hearts.ui.login.LoginViewModel
import com.hino.hearts.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dihardja Software on 2020-02-10.
 */
val moduleViewModel = module {
    viewModel { SplashViewModel() }

    viewModel { LoginViewModel() }

    viewModel { HomeViewModel() }
    viewModel { HomeFragmentViewModel() }
}
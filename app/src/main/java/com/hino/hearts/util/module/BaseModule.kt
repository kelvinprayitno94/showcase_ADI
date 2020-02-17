package com.hino.hearts.util.module

import com.hino.hearts.ui.approval.category.ApprovalTabViewModel
import com.hino.hearts.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dihardja Software on 2020-02-10.
 */
val moduleViewModel = module {
    viewModel {
        LoginViewModel()
        ApprovalTabViewModel()
    }
}
package com.hino.hearts.util.module

import com.hino.hearts.ui.account.AccountListActivityViewModel
import com.hino.hearts.ui.account.detail.contact.AccountContactViewModel
import com.hino.hearts.ui.account.detail.accDetail.AccountDetailViewModel
import com.hino.hearts.ui.account.detail.accDetail.editAccount.EditAccountDetailViewModel
import com.hino.hearts.ui.account.detail.opportunity.AccountOpportunityViewModel
import com.hino.hearts.ui.account.detail.vehicles.AccountVehiclesViewModel
import com.hino.hearts.ui.approval.category.ApprovalTabViewModel
import com.hino.hearts.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dihardja Software on 2020-02-10.
 */
val moduleViewModel = module {
    viewModel { LoginViewModel() }
    viewModel { ApprovalTabViewModel() }
    viewModel { AccountContactViewModel() }
    viewModel { AccountDetailViewModel() }
    viewModel { AccountListActivityViewModel() }
    viewModel { AccountVehiclesViewModel() }
    viewModel { EditAccountDetailViewModel() }
    viewModel { AccountOpportunityViewModel() }

}
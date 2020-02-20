package com.hino.hearts.util.module

import com.hino.hearts.ui.event.EventViewModel
import com.hino.hearts.ui.home.HomeFragmentViewModel
import com.hino.hearts.ui.home.HomeViewModel
import com.hino.hearts.ui.approval.ApprovalTabViewModel
import com.hino.hearts.ui.login.LoginViewModel
import com.hino.hearts.ui.notification.NotificationDetailViewModel
import com.hino.hearts.ui.notification.NotificationViewModel
import com.hino.hearts.ui.onboarding.OnboardingViewModel
import com.hino.hearts.ui.onboarding.first.OnboardingFirstViewModel
import com.hino.hearts.ui.onboarding.second.OnboardingSecondViewModel
import com.hino.hearts.ui.onboarding.third.OnboardingThirdViewModel
import com.hino.hearts.ui.opportunity.detail.ActivitiesViewModel
import com.hino.hearts.ui.opportunity.detail.InformationViewModel
import com.hino.hearts.ui.pendingtransactions.PendingTransactionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dihardja Software on 2020-02-10.
 */
val moduleViewModel = module {
    viewModel { OnboardingViewModel() }
    viewModel { OnboardingFirstViewModel() }
    viewModel { OnboardingSecondViewModel() }
    viewModel { OnboardingThirdViewModel() }

    viewModel {LoginViewModel() }

    viewModel {ApprovalTabViewModel() }

    viewModel { HomeViewModel() }
    viewModel { HomeFragmentViewModel() }

    viewModel { NotificationViewModel() }
    viewModel { NotificationDetailViewModel() }

    viewModel { PendingTransactionsViewModel() }

    viewModel { EventViewModel() }

    viewModel { InformationViewModel() }
    viewModel { ActivitiesViewModel() }
}
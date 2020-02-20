package com.hino.hearts.util.module

import com.hino.hearts.ui.event.EventDetailViewModel
import com.hino.hearts.ui.event.EventViewModel
import com.hino.hearts.ui.event.attendees.AttendeesViewModel
import com.hino.hearts.ui.event.eventinformation.EventInformationViewModel
import com.hino.hearts.ui.home.HomeFragmentViewModel
import com.hino.hearts.ui.home.HomeViewModel
import com.hino.hearts.ui.login.LoginViewModel
import com.hino.hearts.ui.notification.NotificationDetailViewModel
import com.hino.hearts.ui.notification.NotificationViewModel
import com.hino.hearts.ui.onboarding.OnboardingViewModel
import com.hino.hearts.ui.onboarding.first.OnboardingFirstViewModel
import com.hino.hearts.ui.onboarding.second.OnboardingSecondViewModel
import com.hino.hearts.ui.onboarding.third.OnboardingThirdViewModel
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

    viewModel { LoginViewModel() }

    viewModel { HomeViewModel() }
    viewModel { HomeFragmentViewModel() }

    viewModel { NotificationViewModel() }
    viewModel { NotificationDetailViewModel() }

    viewModel { PendingTransactionsViewModel() }

    viewModel { EventViewModel() }
    viewModel { EventDetailViewModel() }
    viewModel { EventInformationViewModel() }
    viewModel { AttendeesViewModel() }
}
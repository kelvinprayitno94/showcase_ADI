package com.hino.hearts.util.module

import com.hino.hearts.ui.account.AccountListActivityViewModel
import com.hino.hearts.ui.account.detail.accDetail.AccountDetailViewModel
import com.hino.hearts.ui.account.detail.accDetail.editAccount.EditAccountDetailViewModel
import com.hino.hearts.ui.account.detail.contact.AccountContactViewModel
import com.hino.hearts.ui.account.detail.opportunity.AccountOpportunityViewModel
import com.hino.hearts.ui.account.detail.vehicles.AccountVehiclesViewModel
import com.hino.hearts.ui.account.detail.vehicles.vehicleDetail.VehicleDetailViewModel
import com.hino.hearts.ui.approval.category.ApprovalTabViewModel
import com.hino.hearts.ui.approval.detail.ApprovalDetailViewModel
import com.hino.hearts.ui.approval.filter.ApprovalFilterViewModel
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

    viewModel { LoginViewModel() }
    viewModel { ApprovalTabViewModel() }
    viewModel { AccountContactViewModel() }
    viewModel { AccountDetailViewModel() }
    viewModel { AccountListActivityViewModel() }
    viewModel { AccountVehiclesViewModel() }
    viewModel { VehicleDetailViewModel() }
    viewModel { AccountOpportunityViewModel() }
    viewModel { EditAccountDetailViewModel() }
//    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { HomeFragmentViewModel() }
    viewModel { NotificationViewModel() }
    viewModel { NotificationDetailViewModel() }
    viewModel { PendingTransactionsViewModel() }

    viewModel { EventViewModel() }
    viewModel { ApprovalFilterViewModel() }
    viewModel { ApprovalDetailViewModel() }
    viewModel { EventDetailViewModel() }
    viewModel { EventInformationViewModel() }
    viewModel { AttendeesViewModel() }

    viewModel { InformationViewModel() }
    viewModel { ActivitiesViewModel() }
}
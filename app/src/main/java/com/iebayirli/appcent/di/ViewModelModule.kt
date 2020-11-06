package com.iebayirli.appcent.di

import com.iebayirli.appcent.ui.achievements.AchievementsViewModel
import com.iebayirli.appcent.ui.home.HomeViewModel
import com.iebayirli.appcent.ui.login.LoginViewModel
import com.iebayirli.appcent.ui.login.ParentLoginViewModel
import com.iebayirli.appcent.ui.main.MainViewModel
import com.iebayirli.appcent.ui.onboarding.OnboardingViewModel
import com.iebayirli.appcent.ui.profile.ProfileViewModel
import com.iebayirli.appcent.ui.splash.SplashViewModel
import com.iebayirli.appcent.ui.transactions.TransactionsViewModel
import com.iebayirli.appcent.ui.user_form.UserFormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { ParentLoginViewModel() }
    viewModel { LoginViewModel() }
    viewModel { UserFormViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AchievementsViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { TransactionsViewModel() }
    viewModel { OnboardingViewModel() }

}
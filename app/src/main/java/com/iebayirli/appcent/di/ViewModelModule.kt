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
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { ParentLoginViewModel() }
    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { UserFormViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AchievementsViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { TransactionsViewModel(get()) }
    viewModel { OnboardingViewModel() }

}
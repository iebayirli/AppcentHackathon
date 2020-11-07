package com.iebayirli.appcent.di

import com.iebayirli.appcent.repository.AchievementRepository
import com.iebayirli.appcent.repository.CampaignsRepository
import com.iebayirli.appcent.repository.LastTransactionsRepository
import com.iebayirli.appcent.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { UserRepository() }
    factory { AchievementRepository() }
    factory { LastTransactionsRepository() }
    factory { CampaignsRepository() }
}
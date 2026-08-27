package id.my.gradien.cloud.core.di

import id.my.gradien.cloud.core.network.HttpClientFactory
import id.my.gradien.cloud.dashboard.presentation.DashboardViewModel
import id.my.gradien.cloud.login.data.LoginRepositoryImpl
import id.my.gradien.cloud.login.data.datasource.LoginDataSource
import id.my.gradien.cloud.login.data.datasource.LoginDataSourceImpl
import id.my.gradien.cloud.login.domain.LoginRepository
import id.my.gradien.cloud.login.presentation.LoginViewModel
import id.my.gradien.cloud.node.list.presentation.NodeListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }

    singleOf(::LoginDataSourceImpl).bind<LoginDataSource>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    factory { LoginViewModel(get()) }
    factory { DashboardViewModel() }
    factory { NodeListViewModel() }
}
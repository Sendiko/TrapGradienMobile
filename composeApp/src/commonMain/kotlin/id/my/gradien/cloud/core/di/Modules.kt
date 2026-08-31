package id.my.gradien.cloud.core.di

import id.my.gradien.cloud.clusters.data.ClusterRepositoryImpl
import id.my.gradien.cloud.clusters.data.datasource.ClusterDataSource
import id.my.gradien.cloud.clusters.data.datasource.ClusterDataSourceImpl
import id.my.gradien.cloud.clusters.domain.ClusterRepository
import id.my.gradien.cloud.core.network.HttpClientFactory
import id.my.gradien.cloud.login.data.LoginRepositoryImpl
import id.my.gradien.cloud.login.data.datasource.LoginDataSource
import id.my.gradien.cloud.login.data.datasource.LoginDataSourceImpl
import id.my.gradien.cloud.login.domain.LoginRepository
import id.my.gradien.cloud.login.presentation.LoginViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }

    singleOf(::LoginDataSourceImpl).bind<LoginDataSource>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    singleOf(::ClusterDataSourceImpl).bind<ClusterDataSource>()
    singleOf(::ClusterRepositoryImpl).bind<ClusterRepository>()

    factory { LoginViewModel(get()) }
}
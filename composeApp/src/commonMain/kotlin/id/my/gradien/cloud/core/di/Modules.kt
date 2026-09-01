package id.my.gradien.cloud.core.di

import id.my.gradien.cloud.clusters.data.ClusterRepositoryImpl
import id.my.gradien.cloud.clusters.data.datasource.ClusterDataSource
import id.my.gradien.cloud.clusters.data.datasource.ClusterDataSourceImpl
import id.my.gradien.cloud.clusters.domain.ClusterRepository
import id.my.gradien.cloud.clusters.presentation.ClustersViewModel
import id.my.gradien.cloud.core.network.HttpClientFactory
import id.my.gradien.cloud.core.session.SessionManager
import id.my.gradien.cloud.core.session.SessionManagerImpl
import id.my.gradien.cloud.login.data.LoginRepositoryImpl
import id.my.gradien.cloud.login.data.datasource.LoginDataSource
import id.my.gradien.cloud.login.data.datasource.LoginDataSourceImpl
import id.my.gradien.cloud.login.domain.LoginRepository
import id.my.gradien.cloud.login.presentation.LoginViewModel
import id.my.gradien.cloud.home.presentation.HomeViewModel
import id.my.gradien.cloud.nodes.data.NodeRepositoryImpl
import id.my.gradien.cloud.nodes.data.datasource.NodeDataSource
import id.my.gradien.cloud.nodes.data.datasource.NodeDataSourceImpl
import id.my.gradien.cloud.nodes.domain.NodeRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::SessionManagerImpl).bind<SessionManager>()

    singleOf(::LoginDataSourceImpl).bind<LoginDataSource>()
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    singleOf(::ClusterDataSourceImpl).bind<ClusterDataSource>()
    singleOf(::ClusterRepositoryImpl).bind<ClusterRepository>()

    singleOf(::NodeDataSourceImpl).bind<NodeDataSource>()
    singleOf(::NodeRepositoryImpl).bind<NodeRepository>()

    factory { LoginViewModel(get(), get()) }
    factory { HomeViewModel(get(), get()) }
    factory { ClustersViewModel(get(), get(), get()) }
}

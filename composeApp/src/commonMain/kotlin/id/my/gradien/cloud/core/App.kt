package id.my.gradien.cloud.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.my.gradien.cloud.clusters.presentation.ClustersScreen
import id.my.gradien.cloud.clusters.presentation.ClustersViewModel
import id.my.gradien.cloud.core.navigation.ClustersScreen as RouteClustersScreen
import id.my.gradien.cloud.core.navigation.HomeScreen as RouteHomeScreen
import id.my.gradien.cloud.core.navigation.LoginScreen as RouteLoginScreen
import id.my.gradien.cloud.core.navigation.NodeListScreen as RouteNodeListScreen
import id.my.gradien.cloud.core.navigation.ProfileScreen as RouteProfileScreen
import id.my.gradien.cloud.core.navigation.SplashScreen as RouteSplashScreen
import id.my.gradien.cloud.core.ui.theme.AppTheme
import id.my.gradien.cloud.home.presentation.HomeScreen
import id.my.gradien.cloud.home.presentation.HomeViewModel
import id.my.gradien.cloud.login.presentation.LoginScreen
import id.my.gradien.cloud.login.presentation.LoginViewModel
import id.my.gradien.cloud.splash.presentation.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import id.my.gradien.cloud.core.di.sharedModules
import org.koin.compose.viewmodel.koinViewModel

import id.my.gradien.cloud.core.navigation.NodeScreen as RouteNodeScreen
import id.my.gradien.cloud.nodes.list.presentation.NodeListScreen
import id.my.gradien.cloud.nodes.list.presentation.NodeListViewModel
import id.my.gradien.cloud.nodes.detail.presentation.NodeDetailScreen
import id.my.gradien.cloud.nodes.detail.presentation.NodeDetailViewModel
import androidx.navigation.toRoute
import androidx.compose.runtime.LaunchedEffect

@Composable
fun App(darkTheme: Boolean = false) {
    AppTheme(
        darkTheme = false
    ) {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val mainTabs = listOf(RouteHomeScreen, RouteClustersScreen, RouteNodeListScreen, RouteProfileScreen)
        val isMainTab = mainTabs.any { currentDestination?.hasRoute(it::class) == true }

        Scaffold(
            bottomBar = {
                if (isMainTab) {
                    NavigationBar {
                        mainTabs.forEach { tab ->
                            val selected = currentDestination?.hasRoute(tab::class) == true
                            val (icon, label) = when (tab) {
                                RouteHomeScreen -> Icons.Default.Dashboard to "Home"
                                RouteClustersScreen -> Icons.Default.Hub to "Clusters"
                                RouteNodeListScreen -> Icons.Default.Sensors to "Nodes"
                                RouteProfileScreen -> Icons.Default.AccountCircle to "Profile"
                                else -> Icons.Default.Dashboard to ""
                            }
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(tab) {
                                        popUpTo(navController.graph.findStartDestination().displayName) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = { Icon(icon, contentDescription = label) },
                                label = { Text(label) }
                            )
                        }
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = RouteLoginScreen,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<RouteSplashScreen> {
                    SplashScreen(
                        onNavigate = {
                            navController.navigate(RouteLoginScreen) {
                                popUpTo(RouteSplashScreen) { inclusive = true }
                            }
                        }
                    )
                }
                composable<RouteLoginScreen> {
                    val viewModel = koinViewModel<LoginViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    LoginScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = {
                            navController.navigate(RouteHomeScreen) {
                                popUpTo(RouteLoginScreen) { inclusive = true }
                            }
                        }
                    )
                }
                composable<RouteHomeScreen> {
                    val viewModel = koinViewModel<HomeViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    HomeScreen(
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
                composable<RouteClustersScreen> {
                    val viewModel = koinViewModel<ClustersViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    ClustersScreen(
                        onEvent = viewModel::onEvent,
                        state = state,
                    )
                }
                composable<RouteNodeListScreen> {
                    val viewModel = koinViewModel<NodeListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    NodeListScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigate = { navController.navigate(it) }
                    )
                }
                composable<RouteNodeScreen> { backStackEntry ->
                    val args = backStackEntry.toRoute<RouteNodeScreen>()
                    val viewModel = koinViewModel<NodeDetailViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(args.id, args.key) {
                        viewModel.initNode(args.id, args.key)
                    }

                    NodeDetailScreen(
                        state = state,
                        onEvent = viewModel::onEvent,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable<RouteProfileScreen> {
                    Surface { Text("Profile Screen Content") }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    KoinApplication(application = {
        modules(sharedModules)
    }) {
        App()
    }
}

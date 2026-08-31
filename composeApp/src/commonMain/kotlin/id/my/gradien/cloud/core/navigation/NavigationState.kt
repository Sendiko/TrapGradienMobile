package id.my.gradien.cloud.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer

/**
 * Create a navigation state that persists config changes and process death.
 */
@Suppress("UNCHECKED_CAST")
@Composable
fun <T : NavKey> rememberNavigationState(
    configuration: SavedStateConfiguration,
    startRoute: T,
    topLevelRoutes: Set<T>
): NavigationState<T> {

    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes, configuration,
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class) as KSerializer<T>)
    ) {
        mutableStateOf(startRoute)
    }

    val backStacks = topLevelRoutes.associateWith { key ->
        rememberNavBackStack(configuration, key) as NavBackStack<T>
    }

    return remember(startRoute, topLevelRoutes, configuration) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks
        )
    }
}

/**
 * State holder for navigation state.
 *
 * @param startRoute - the start route. The user will exit the app through this route.
 * @param topLevelRoute - the current top level route
 * @param backStacks - the back stacks for each top level route
 */
class NavigationState<T : NavKey>(
    val startRoute: T,
    topLevelRoute: MutableState<T>,
    val backStacks: Map<T, NavBackStack<T>>
) {
    var topLevelRoute: T by topLevelRoute
    val stacksInUse: List<T>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }
}

/**
 * Convert NavigationState into NavEntries.
 */
@Composable
fun <T : NavKey> NavigationState<T>.toEntries(
    entryProvider: (T) -> NavEntry<T>
): SnapshotStateList<NavEntry<T>> {

    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<T>(),
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )
    }

    return stacksInUse
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}

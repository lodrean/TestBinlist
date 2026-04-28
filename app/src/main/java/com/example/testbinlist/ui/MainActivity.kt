package com.example.testbinlist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.TestBinlistTheme
import com.example.testbinlist.ui.composeUi.HistoryScreen
import com.example.testbinlist.ui.composeUi.SearchScreen
import com.example.testbinlist.ui.navigation.TopLevelRoute
import com.example.testbinlist.viewmodels.SearchViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val topLevelRoutes = listOf(
            TopLevelRoute("Search", "search", Icons.Filled.Search),
            TopLevelRoute("History", "history", Icons.Filled.Done)
        )
        enableEdgeToEdge()
        setContent {
            TestBinlistTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val searchViewModel: SearchViewModel = koinViewModel()

                LaunchedEffect(Unit) {
                    searchViewModel.snackbarEvent.collect { message ->
                        snackbarHostState.showSnackbar(message)
                    }
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        topLevelRoutes.forEach { topLevelRoute ->
                            NavigationBarItem(icon = {
                                Icon(
                                    imageVector = topLevelRoute.icon,
                                    contentDescription = topLevelRoute.name
                                )
                            },
                                label = { Text(topLevelRoute.name) },
                                selected = currentDestination?.hierarchy?.any { it.route == topLevelRoute.route } == true,
                                onClick = {
                                    navController.navigate(topLevelRoute.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                })
                        }
                    }
                }) { innerPadding ->
                    NavHost(
                        navController, startDestination = "search", Modifier.padding(innerPadding)
                    ) {
                        composable("search") { SearchScreen(viewModel = searchViewModel) }
                        composable("history") { HistoryScreen(triggerHistory = true) }
                    }
                }
            }
        }
    }
}




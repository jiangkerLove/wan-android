package com.study.wan_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.study.wan_android.ui.page.classify.SystemPage
import com.study.wan_android.ui.page.main.MainPage
import com.study.wan_android.ui.theme.WanandroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WanandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                Route.routes.forEach { route ->
                                    BottomNavigationItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                                        onClick = {
                                            navController.navigate(route.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        label = {
                                            Text(text = stringResource(route.resourceId))
                                        },
                                        icon = {
                                            Icon(
                                                painter = painterResource(id = route.drawableId),
                                                contentDescription = "",
                                                modifier = Modifier.size(25.dp)
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Route.Main.route,
                            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
                        ) {
                            composable(Route.Main.route) {
                                MainPage()
                            }
                            composable(Route.Classify.route) {
                                SystemPage()
                            }
                            composable(Route.Mine.route) {
                                Text(text = Route.Mine.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WanandroidTheme {
        Greeting("Android")
    }
}
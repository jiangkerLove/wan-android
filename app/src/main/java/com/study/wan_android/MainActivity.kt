package com.study.wan_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.study.wan_android.ui.page.main.MainPage
import com.study.wan_android.ui.theme.WanandroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val selectIndex by remember { mutableStateOf(0) }
            WanandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
                                Route.navigationList.mapIndexed { index, s ->
                                    BottomNavigationItem(
                                        selected = selectIndex == index,
                                        onClick = { navController.navigate(s) },
                                        label = {
                                            Text(text = s)
                                        },
                                        icon = {
                                            Icon(
                                                bitmap = ImageBitmap(width = 50, height = 50),
                                                contentDescription = ""
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Route.MAIN,
                            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
                        ) {
                            composable(Route.MAIN) {
                                MainPage()
                            }
                            composable(Route.MINE) {
                                Text(text = Route.MINE)
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
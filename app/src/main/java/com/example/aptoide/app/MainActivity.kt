package com.example.aptoide.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aptoide.app.detail.DetailScreen
import com.example.aptoide.app.main.MainScreen
import com.example.aptoide.app.ui.theme.AptoideTheme
import com.example.aptoide.app.ui.theme.CustomColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AptoideTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //the app layout is sorted here vertically from top to bottom in this Column
                    Column {

                        //Empty TopAppBar with the orange color gradient
                        TopAppBar(modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth(),
                            content = { Box(modifier = Modifier
                                .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        CustomColors.Orange200,
                                        CustomColors.Orange500
                                    )
                                )
                            ).fillMaxSize())}
                        )

                        //Navigation composable which encompasses the whole navigation logic
                        //and displays appropriate screen composable
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }

    @Composable
    fun Navigation(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen( viewModel, navController)
            }
            composable("detail") {
                DetailScreen(viewModel, navController)
            }
        }
    }
}


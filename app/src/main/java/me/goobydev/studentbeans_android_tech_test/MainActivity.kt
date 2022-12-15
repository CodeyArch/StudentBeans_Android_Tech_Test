package me.goobydev.studentbeans_android_tech_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation.LoginScreen
import me.goobydev.studentbeans_android_tech_test.feature_photos_screen.presentation.PhotosScreen
import me.goobydev.studentbeans_android_tech_test.ui.theme.StudentBeans_Android_Tech_TestTheme
import me.goobydev.studentbeans_android_tech_test.util.Screen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentBeans_Android_Tech_TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(Screen.LoginScreen.route) {
                            LoginScreen(navController = navController)
                        }
                        composable(Screen.PhotosScreen.route) {
                            PhotosScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


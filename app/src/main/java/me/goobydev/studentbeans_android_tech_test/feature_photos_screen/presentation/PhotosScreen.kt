package me.goobydev.studentbeans_android_tech_test.feature_photos_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.goobydev.studentbeans_android_tech_test.feature_photos_screen.presentation.components.BackTopAppBar
import me.goobydev.studentbeans_android_tech_test.feature_photos_screen.presentation.components.PhotoItem
import me.goobydev.studentbeans_android_tech_test.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PhotosScreen(
    navController: NavController,
    viewModel: PhotosViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(Unit, block = {
        viewModel.getPhotosList()
    })
    Scaffold(
        topBar = {
            BackTopAppBar(
                onNavIconClick = { navController.navigate(Screen.LoginScreen.route) },
                title = "Photos"
            )
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            println(viewModel.photosList)
            items(viewModel.photosList) { photo ->
                PhotoItem(photo = photo)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
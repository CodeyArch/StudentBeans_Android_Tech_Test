package me.goobydev.studentbeans_android_tech_test.util

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object PhotosScreen: Screen("photos_screen")
}

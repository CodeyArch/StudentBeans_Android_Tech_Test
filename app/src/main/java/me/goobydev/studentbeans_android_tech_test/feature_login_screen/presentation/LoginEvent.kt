package me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation

import androidx.compose.ui.focus.FocusState

sealed class LoginEvent {
    data class EnteredEmail(val value: String): LoginEvent()
    data class ChangeEmailFocus(val focusState: FocusState): LoginEvent()

    data class EnteredPassword(val value: String): LoginEvent()
    data class ChangePasswordFocus(val focusState: FocusState): LoginEvent()

    object OpenPhotos: LoginEvent()
}

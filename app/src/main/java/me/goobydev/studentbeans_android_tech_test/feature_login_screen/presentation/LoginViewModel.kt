package me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation.components.TransparentTextFieldState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {
    private val _enteredEmail = mutableStateOf(TransparentTextFieldState(hint = "Email"))
    val enteredEmail: State<TransparentTextFieldState> = _enteredEmail

    private val _enteredPassword = mutableStateOf(TransparentTextFieldState(hint = "Password"))
    val enteredPassword: State<TransparentTextFieldState> = _enteredPassword

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EnteredEmail -> {
                _enteredEmail.value = enteredEmail.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.ChangeEmailFocus -> {
                _enteredEmail.value = enteredEmail.value.copy(
                    isHintVisible = !event.focusState.isFocused && enteredEmail.value.text.isBlank(),
                    isFocused = event.focusState.isFocused
                )
            }
            is LoginEvent.EnteredPassword -> {
                _enteredPassword.value = enteredPassword.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.ChangePasswordFocus -> {
                _enteredPassword.value = enteredPassword.value.copy(
                    isHintVisible = !event.focusState.isFocused && enteredPassword.value.text.isBlank(),
                    isFocused = event.focusState.isFocused
                )
            }
            is LoginEvent.OpenPhotos -> {
                if (enteredPassword.value.text.isBlank() || enteredEmail.value.text.isBlank()) {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(message = "Username and/or Password cannot be empty")
                        )
                    }
                } else {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UIEvent.OpenPhotos
                        )
                    }
                }
            }
        }
    }
    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
        object OpenPhotos: UIEvent()
    }
}
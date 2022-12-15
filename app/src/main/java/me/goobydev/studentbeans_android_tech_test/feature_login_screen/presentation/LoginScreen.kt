package me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation.components.TransparentTextField
import me.goobydev.studentbeans_android_tech_test.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    isTest: Boolean = false,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.enteredEmail.value
    val passwordState = viewModel.enteredPassword.value
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is LoginViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message // Errors with saving
                    )
                }
                is LoginViewModel.UIEvent.OpenPhotos -> {
                    if (isTest) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Success, this would be a navigation"
                        )
                    } else navController.navigate(Screen.PhotosScreen.route)

                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Log in to your Student Beans account",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(32.dp))
            TransparentTextField(
                text = emailState.text,
                hint = emailState.hint,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                onFocusChange = {
                    viewModel.onEvent(LoginEvent.ChangeEmailFocus(it))
                },
                isHintVisible = emailState.isHintVisible,
                isFocused = emailState.isFocused,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .semantics(mergeDescendants = false) { contentDescription = "Email" }
                    .semantics { setText{ true } }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                text = passwordState.text,
                hint = passwordState.hint,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                onFocusChange = {
                    viewModel.onEvent(LoginEvent.ChangePasswordFocus(it))
                },
                isHintVisible = passwordState.isHintVisible,
                isFocused = passwordState.isFocused,
                isPasswordType = true,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .semantics(mergeDescendants = false) { contentDescription = "Password" }
                    .semantics { setText{ true } }
            )
            Spacer(modifier = Modifier.height(16.dp))
            /* I choose to use Box here instead of Button as Box is more easily customisable
            and can cleanly meet the design specs */
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.primary)
                    .clickable {
                        viewModel.onEvent(LoginEvent.OpenPhotos)
                    }
                    .padding(16.dp),

            ) {
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}
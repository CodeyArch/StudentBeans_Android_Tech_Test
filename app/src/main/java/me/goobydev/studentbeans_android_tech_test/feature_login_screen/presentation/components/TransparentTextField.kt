package me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import me.goobydev.studentbeans_android_tech_test.ui.theme.BoxGray

data class TransparentTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isFocused: Boolean = false
)

@Composable
fun TransparentTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    isFocused: Boolean = false,
    isPasswordType: Boolean = false,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    onFocusChange: (FocusState) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(4.dp))
            .background(BoxGray)
            .border(
                width = 1.dp,
                color = if(isFocused) MaterialTheme.colors.primary else BoxGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp),
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                },
            visualTransformation = if (isPasswordType) {
                PasswordVisualTransformation()
            } else VisualTransformation.None
        )
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = Color.Gray)
        }
    }
}
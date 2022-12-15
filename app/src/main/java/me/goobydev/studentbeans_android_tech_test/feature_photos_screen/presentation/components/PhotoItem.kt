package me.goobydev.studentbeans_android_tech_test.feature_photos_screen.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import me.goobydev.studentbeans_android_tech_test.feature_photos_screen.domain.model.Photo
import me.goobydev.studentbeans_android_tech_test.ui.theme.BoxGray

@Composable
fun PhotoItem(
    photo: Photo
) {
    Box(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp))
        .background(BoxGray)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = photo.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = photo.title,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}
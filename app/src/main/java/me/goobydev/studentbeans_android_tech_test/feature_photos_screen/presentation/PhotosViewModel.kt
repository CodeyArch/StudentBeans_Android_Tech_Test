package me.goobydev.studentbeans_android_tech_test.feature_photos_screen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.goobydev.studentbeans_android_tech_test.feature_photos_screen.domain.model.APIService
import me.goobydev.studentbeans_android_tech_test.feature_photos_screen.domain.model.Photo
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor() : ViewModel() {
    private val _photosList = mutableStateListOf<Photo>()
    private var errorMessage: String by mutableStateOf("")
    val photosList: List<Photo>
    get() = _photosList

    fun getPhotosList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _photosList.clear()
                _photosList.addAll(apiService.getPhotos())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}
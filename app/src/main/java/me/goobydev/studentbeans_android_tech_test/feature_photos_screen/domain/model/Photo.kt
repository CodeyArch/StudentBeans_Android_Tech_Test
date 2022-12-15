package me.goobydev.studentbeans_android_tech_test.feature_photos_screen.domain.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Photo(
    val albumId: Int, // The album the photo relates to
    val id: Int, // The unique id for each photo
    val title: String, // Title to accompany image
    val url: String, // Where clicking the photo will take them
    val thumbnailUrl: String // Image used for thumbnail
)
const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface APIService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>

    companion object {
        private var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}

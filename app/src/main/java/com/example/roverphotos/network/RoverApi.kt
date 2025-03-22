package com.example.roverapi.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

private val json = Json {
    ignoreUnknownKeys = true // Avoids errors if API adds new fields
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

// Data model for API response
@Serializable
data class MarsPhoto(
    val id: Int,
    val img_src: String
)

@Serializable
data class MarsPhotoResponse(
    val photos: List<MarsPhoto>
)

interface RoverApiService {
    @GET("rovers/curiosity/photos")
    suspend fun getRoverPhotosBySol(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): MarsPhotoResponse

    @GET("rovers/curiosity/photos")
    suspend fun getRoverPhotosByEarthDate(
        @Query("earth_date") earthDate: String,
        @Query("api_key") apiKey: String
    ): MarsPhotoResponse
}

object RoverApi {
    val retrofitService: RoverApiService by lazy {
        retrofit.create(RoverApiService::class.java)
    }
}
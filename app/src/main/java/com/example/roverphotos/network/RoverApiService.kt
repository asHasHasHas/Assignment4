package com.example.roverapi.network

import com.example.roverphotos.model.RoverPhotoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.nasa.gov/mars-photos/api/v1/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON responses
    .baseUrl(BASE_URL)
    .build()


interface RoverApiService {
    @GET("rovers/{rover}/photos")
    suspend fun getRoverPhotos(
        @Path("rover") rover: String, // Allows fetching photos from any rover
        @Query("earth_date") earthDate: String?, // Optional: Get photos by Earth date
        @Query("sol") sol: Int?, // Optional: Get photos by Martian sol
        @Query("api_key") apiKey: String = "DEMO_KEY"
    ): RoverPhotoResponse
}

object RoverApi {
    val retrofitService: RoverApiService by lazy {
        retrofit.create(RoverApiService::class.java)
    }
}

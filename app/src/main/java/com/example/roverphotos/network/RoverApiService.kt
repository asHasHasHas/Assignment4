package com.example.roverapi.network

import com.example.roverphotos.model.Rover
import com.example.roverphotos.model.RoverPhotoResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

interface RoverApiService {
    @GET("rovers/{rover}/photos")
    suspend fun getRoverPhotos(
        @Path("rover") roverName: String,
        @Query("earth_date") earthDate: String,  // Ensuring Earth date filtering
        @Query("api_key") apiKey: String = "DEMO_KEY"
    ): Response<RoverPhotoResponse>
}

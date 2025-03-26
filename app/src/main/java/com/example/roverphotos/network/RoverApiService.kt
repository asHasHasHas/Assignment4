package com.example.roverapi.network

import com.example.roverphotos.model.Rover
import com.example.roverphotos.model.RoverPhotoResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RoverApiService {
    @GET("rovers?api_key=DEMO_KEY")
    suspend fun getRovers(): Response<ArrayList<Rover>>

    companion object {
        val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

        fun create() : RoverApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON responses
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RoverApiService::class.java)
        }
    }
}


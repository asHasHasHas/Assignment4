package com.example.roverphotos.model

import com.google.gson.annotations.SerializedName

data class Rover(
    @SerializedName("id") val id: String,
    @SerializedName("earth_date") val earthDate: String,
    @SerializedName("camera") val camera: Camera,
    @SerializedName("rover") val rover: Rover,
    @SerializedName("rover") val launchDate: Int
)
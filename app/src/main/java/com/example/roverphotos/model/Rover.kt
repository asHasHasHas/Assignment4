package com.example.roverphotos.model

import com.google.gson.annotations.SerializedName

data class Rover(
    @SerializedName("name") val name: String,
    @SerializedName("launch_date") val launchDate: String,
    @SerializedName("img_src") val imgSrc: String
)
package com.example.marsphotos.model

import com.google.gson.annotations.SerializedName

data class Rover(
    @SerializedName("name") val name: String
)
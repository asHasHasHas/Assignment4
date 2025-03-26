package com.example.marsphotos.model

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("full_name") val fullName: String
)

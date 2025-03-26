package com.example.roverphotos.model

import com.google.gson.annotations.SerializedName

data class RoverPhotoResponse(
    @SerializedName("photos") val photos: List<RoverPhoto>
)
package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoverPhotoResponse(
    @SerialName("photos") val photos: List<RoverPhoto>
)

package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhotoResponse(
    @SerialName("photos") val photos: List<MarsPhoto>
)

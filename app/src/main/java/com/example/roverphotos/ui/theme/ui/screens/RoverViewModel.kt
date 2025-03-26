/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.roverphotos.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roverapi.network.RoverApi
import com.example.roverapi.network.RoverApiService
import com.example.roverphotos.model.RoverPhoto
import kotlinx.coroutines.launch

class RoverViewModel : ViewModel() {
    // State for holding the photo data and loading/error states
    var roverPhoto = mutableStateOf<RoverPhoto?>(null)
    var isLoading = mutableStateOf(true)
    var errorMessage = mutableStateOf<String?>(null)

    // Function to fetch rover photos
    fun getRoverPhoto(roverName: String, earthDate: String) {
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch {
            try {
                val response = RoverApi.retrofitService.getRoverPhotos(roverName, earthDate, null)
                if (response.photos.isNotEmpty()) {
                    roverPhoto.value = response.photos[0] // Assuming you want the first photo
                } else {
                    errorMessage.value = "No photos available for this date"
                }
                isLoading.value = false
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
                isLoading.value = false
            }
        }
    }
}
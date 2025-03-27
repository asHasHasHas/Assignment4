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

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roverapi.network.RoverApiService
import com.example.roverphotos.model.Rover
import com.example.roverphotos.model.RoverPhoto
import kotlinx.coroutines.launch
import retrofit2.Response

class RoverViewModel : ViewModel() {
    private val roverApi = RoverApiService.create() // API service instance

    // LiveData for storing the list of photos
    val _roverPhotos = MutableLiveData<Response<ArrayList<Rover>>>()
    val roverPhotos: LiveData<Response<ArrayList<Rover>>> = _roverPhotos

    // Function to get rover photos data
    fun getData() {
        viewModelScope.launch {
            try {
                val response = roverApi.getRovers() // Fetch rover photos
                if (response.isSuccessful) {
                    // Handle the photos data response
                    _roverPhotos.value = response
                } else {
                    Log.d("API Error", "Failed to load data")
                }
            } catch (e: Exception) {
                Log.d("Network Error", e.message ?: "Unknown error")
            }
        }
    }
}

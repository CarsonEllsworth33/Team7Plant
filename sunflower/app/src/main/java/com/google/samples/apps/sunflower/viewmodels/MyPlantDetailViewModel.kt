/*
 * Copyright 2018 Google LLC
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

package com.google.samples.apps.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.samples.apps.sunflower.BuildConfig
import com.google.samples.apps.sunflower.PlantDetailFragment
import com.google.samples.apps.sunflower.data.GreenhousePlantRepository
import com.google.samples.apps.sunflower.data.MyPlantRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

/**
 * The ViewModel used in [PlantDetailFragment].
 */
class MyPlantDetailViewModel @AssistedInject constructor(
        plantRepository: MyPlantRepository,
        private val greenhousePlantRepository: GreenhousePlantRepository,
        @Assisted private val plantId: Int
) : ViewModel() {

    val isPlanted = greenhousePlantRepository.isPlanted(plantId)
    val plant = plantRepository.getPlantID(plantId)

    fun addPlantToGarden() {
        viewModelScope.launch {
            greenhousePlantRepository.createGreenhousePlant(plantId)
        }
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(plantId: Int): PlantDetailViewModel
    }

    companion object {
        fun provideFactory(
                assistedFactory: AssistedFactory,
                plantId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(plantId) as T
            }
        }
    }
}

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

import com.google.samples.apps.sunflower.data.PlantAndGreenhousePlant
import java.text.SimpleDateFormat
import java.util.Locale

class PlantAndGreenhousePlantsViewModel(plantings: PlantAndGreenhousePlant) {
    private val plant = checkNotNull(plantings.plant)
    private val greenhousePlant = plantings.greenhousePlants[0]

   // val waterDateString: String = dateFormat.format(greenhousePlant.lastWateringDate.time)
    val wateringAmount
        get() = plant.watering
    val imageUrl
        get() = plant.imageurl
    val plantName
        get() = plant.name
    val plantDateString: String = dateFormat.format(greenhousePlant.plantDate.time)
    val plantId
        get() = plant.id
    val min_temp
        get() = plant.min_temp
    val max_temp
        get() = plant.max_temp
    val aciditiy
        get() = plant.aciditity
    val genus
        get() = plant.genus
    val species
        get() = plant.species
    val desc
        get() = plant.description


    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}

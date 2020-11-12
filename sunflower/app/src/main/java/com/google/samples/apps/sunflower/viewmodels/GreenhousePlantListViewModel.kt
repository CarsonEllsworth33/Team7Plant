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

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.samples.apps.sunflower.data.GreenhousePlantRepository
import com.google.samples.apps.sunflower.data.PlantAndGreenhousePlant

class GreenhousePlantListViewModel @ViewModelInject internal constructor(
        greenhousePlantRepository: GreenhousePlantRepository
) : ViewModel() {
    val plantAndGreenhousePlant: LiveData<List<PlantAndGreenhousePlant>> =
            greenhousePlantRepository.getGreenhousePlants()
}

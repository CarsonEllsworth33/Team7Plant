/**
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


package com.google.samples.apps.sunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


/**
 * The Data Access Object for the [GreenhousePlant] class.
 */

@Dao
interface GreenhousePlantDao {
    @Query("SELECT * FROM greenhouse_plants")
    fun getGreenhousePlants(): LiveData<List<GreenhousePlant>>

    @Query("SELECT EXISTS(SELECT 1 FROM greenhouse_plants WHERE plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: Int): LiveData<Boolean>


/**
     * This query will tell Room to query both the [Plant] and [GreenhousePlant] tables and handle
     * the object mapping.
     */

    @Transaction
    @Query("SELECT * FROM myplants WHERE id IN (SELECT DISTINCT(plant_id) FROM greenhouse_plants)")
    fun getPlantedGreenhouse(): LiveData<List<PlantAndGreenhousePlant>>

    @Insert
    suspend fun insertGreenhousePlant(greenhousePlant: GreenhousePlant): Long

    @Delete
    suspend fun deleteGreenhousePlant(greenhousePlant: GreenhousePlant)
}


/*
 * Copyright 2020 Google LLC
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
import androidx.room.*

// data access object for MyPlant class

@Dao
interface MyPlantDao {
    @Query("SELECT * FROM My_Plants ORDER BY name")
    fun getPlants(): LiveData<List<MyPlant>>

    /*
    @Query("SELECT * FROM My_Plants WHERE health = 0")
    fun getUnhealthy: LiveData<List<MyPlant>>
    */
    @Query("SELECT * FROM My_Plants WHERE name LIKE :name")
    fun getPlant(name: String): LiveData<List<MyPlant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<MyPlant>)

    @Delete
    fun deletePlant(plants: MyPlant)
}
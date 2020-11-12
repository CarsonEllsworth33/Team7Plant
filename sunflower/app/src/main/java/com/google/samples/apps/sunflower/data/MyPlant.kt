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

import androidx.room.Entity
import androidx.room.PrimaryKey

// Where does the sensor data go...?
@Entity(tableName = "myplants")
data class MyPlant (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val genus: String,
    val species: String?,
    val description: String,
    val min_temp: Int,
    val max_temp: Int,
    val aciditity: Int,
    val watering: Int,
    val imageurl: String = ""
)
{
    // Function code for determining water needs
    // pH needs
    // temperature needs
    override fun toString() = name
}

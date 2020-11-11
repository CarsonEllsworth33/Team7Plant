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

package com.google.samples.apps.sunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

/**
 * [GreenhousePlant] represents when a user adds a [MyPlant] to their garden, with useful metadata.
 * Properties are used for notifications (such as when to water the
 * plant), health can be calculated, and sensor data can be stored. Maybe user can add notes to each plant.
 *
 * Declaring the column info allows for the renaming of variables without implementing a
 * database migration, as the column name would not change.
 */
@Entity(
        tableName = "Greenhouse_Plants",
        foreignKeys = [
            ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])
        ],
        indices = [Index("plant_id")]
)
data class GreenhousePlant(
        @ColumnInfo(name = "plant_id") val plantId: String,

        /**
         * Indicates when the [Plant] was planted. Used for showing notification when it's time
         * to harvest the plant.
         */
        @ColumnInfo(name = "add_date") val plantDate: Calendar = Calendar.getInstance(),

        /**
         * Indicates when the [Plant] was last watered. Used for showing notification when it's
         * time to water the plant.
         */
        /**
         * Columns to add: foreign keys pointing to sensor data ?, user added notes, later customize values?
         */
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var greenhouseId: Long = 0
}

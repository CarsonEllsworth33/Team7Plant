package com.google.samples.apps.sunflower.data

import androidx.room.*
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR
import java.util.Date

@Entity(tableName = "sensors",
        foreignKeys = [ForeignKey(entity = GardenPlanting::class, parentColumns = ["gardenPlantingId"], childColumns = ["gId"])],
        indices = [Index("gId"), Index("time")])

data class Sensors(
        @PrimaryKey
        val gId: Long,
        @PrimaryKey
        val time: Date,
        val sensor: String,
        val value: Int
) {


}

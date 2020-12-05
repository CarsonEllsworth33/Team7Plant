package com.google.samples.apps.sunflower.data

import androidx.room.*
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR
import java.util.Date

@Entity(tableName = "sensors",
        //foreignKeys = [ForeignKey(entity = GardenPlanting::class, parentColumns = ["id"], childColumns = ["gId"])],
        indices = [Index("gId"), Index("time")],
        //primaryKeys=["gId", "time"])
)

data class Sensors(
        @PrimaryKey(autoGenerate = true)
        val gId: Long,
        val time: String,
        val sensor: String,
        val value: Int
) {


}

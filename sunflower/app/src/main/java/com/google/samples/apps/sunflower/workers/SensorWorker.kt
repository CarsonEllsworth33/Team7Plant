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

package com.google.samples.apps.sunflower.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.impl.background.greedy.GreedyScheduler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.samples.apps.sunflower.data.AppDatabase
import com.google.samples.apps.sunflower.data.GardenPlanting
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.data.Sensors
import com.google.samples.apps.sunflower.utilities.GREENHOUSE_DATA_FILENAME
import com.google.samples.apps.sunflower.utilities.PLANT_DATA_FILENAME
import com.google.samples.apps.sunflower.utilities.SENSOR_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileReader
// val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
// val checkSensorsRequest = PeriodicWorkRequestBuilder<SensorWorker>().setConstraints(constraints).build()
//https://raw.githubusercontent.com/BBowdon00/plant_json/main/sensor.json
final BASE_URL : String = "https://raw.githubusercontent.com/BBowdon00/plant_json/main"
class SensorWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {// access http request
        try {
            //val success: Boolean
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->

                    applicationContext.assets.open(SENSOR_DATA_FILENAME).also { inputStream ->
                        JsonReader(inputStream.reader()).also { jsonReader ->
                            sensorsList = Gson().fromJson(jsonReader, sensorsType)
                        }
                    }
                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                    database.gardenPlantingDao().insertGardenPlantings(greenhouseList)
                    database.sensorsDao().insertAll(sensorsList)

                }

                Result.success()
            }
            /*
            applicationContext.assets.open(SENSOR_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val sensorsType = object : TypeToken<List<Sensors>>() {}.type
                    val sensorsList: List<Sensors> = Gson().fromJson(jsonReader, sensorsType)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.sensorsDao().insertAll(sensorsList)

                    Result.success()
                }*/
        }
        catch (ex: Exception) {
            Log.e(TAG, "Error fetching online database", ex)
            Result.failure()
        }
    }
    fun networkCall()
    {
        val BASE_URL = "https://raw.githubusercontent.com/BBowdon00/plant_json/main/"
        Retrofit retro = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}

interface sensorEndpoint
{
    @GET("sensor.json")
    fun getSensors()
}

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
import android.util.Log.INFO
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
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.File
import java.io.FileReader
// val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
// val checkSensorsRequest = PeriodicWorkRequestBuilder<SensorWorker>().setConstraints(constraints).build()
//https://raw.githubusercontent.com/BBowdon00/plant_json/main/sensor.json
var sensors_data : List<Sensors> = emptyList()
class SensorWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {// access http request
        try {
            //val success: Boolean
                val BASE_URL = "https://raw.githubusercontent.com/BBowdon00/plant_json/main/"
                val retro = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                val service = retro.create(sensorEndpoint::class.java)
                val call = service.getSensors()
                var called = false
                var sensors_data : List<Sensors>? = emptyList()
                //applicationContext.assets.open(SENSOR_DATA_FILENAME).use{inputStream ->
                 //   JsonReader(inputStream.reader()).use {
                        call.enqueue(object: Callback<List<Sensors>> {
                            override fun onResponse(call: Call<List<Sensors>>, response: Response<List<Sensors>>) {
                                if(response.code()==200)
                                {
                                    Log.d(TAG, "GOOD CODE")
                                }
                                System.out.print(response.toString())
                               sensors_data = response.body()
                                System.out.print("Response: " + response.body())
                                Log.i(TAG, "RECEIVED SENSOR DATA FROM INTERNET")
                                System.out.println("RECEIVED SENSOR DATA FROM INTERNET")
                                System.out.print(sensors_data)
                                called = true


                            }

                            override fun onFailure(call: Call<List<Sensors>>, t: Throwable) {
                                Log.e(TAG, "Error seeding sensor data.")
                                print("ONLINE ERROR")
                            }

                        })

            while(called != true)
            {
                delay(1000)
            }
            val database = AppDatabase.getInstance(applicationContext)
            database.sensorsDao().insertAll(sensors_data)
            Log.d(TAG, "Sensor data has been inserted")
            System.out.println("Sensor data is: " + sensors_data)
            called = false
                    //}
                    Result.success()
                //}
            }

        catch (ex: Exception) {
            Log.e(TAG, "Error fetching online database", ex)
            Result.failure()
        }
    }
    companion object {
        private const val TAG = "SensorWorker"
    }
}

interface sensorEndpoint
{
    @GET("sensor.json")
    fun getSensors() : Call<List<Sensors>>
}


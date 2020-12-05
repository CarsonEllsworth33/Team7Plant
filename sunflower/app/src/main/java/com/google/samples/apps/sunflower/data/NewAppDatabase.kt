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
 *//*


package com.google.samples.apps.sunflower.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.samples.apps.sunflower.utilities.DATABASE_NAME
import com.google.samples.apps.sunflower.workers.SeedDatabaseWorker

*/
/**
 * The Room database for this app
 *//*

@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewAppDatabase : RoomDatabase() {
    abstract fun greenhousePlantDao(): GreenhousePlantDao
    abstract fun myplantDao(): MyPlantDao

    companion object {

        // For Singleton instantiation
        // this ensures only one instance of the database is used everywhere in the app
        @Volatile private var instance: NewAppDatabase? = null

        fun getInstance(context: Context): NewAppDatabase {
            return instance ?: synchronized(this) { // this makes database transactions occur serially and safely
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): NewAppDatabase {
            return Room.databaseBuilder(context, NewAppDatabase::class.java, DATABASE_NAME)
                    .addCallback(
                            object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                                    WorkManager.getInstance(context).enqueue(request)
                                }
                            }
                    )
                    .build()
        }
    }
}
*/

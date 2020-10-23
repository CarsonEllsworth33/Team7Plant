/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.example.plant_app

import android.content.Context
import org.json.JSONException
import org.json.JSONObject


class Recipe(
    val title: String,
    val description: String,
    val imageUrl: String,
    val instructionUrl: String,
    val label: String) {

  companion object {

    fun getRecipesFromFile(filename: String, context: Context): ArrayList<Recipe> {
      val recipeList = ArrayList<Recipe>()

      try {
        // Load data
        val jsonString = loadJsonFromAsset("db.json", context)
        val json = JSONObject(jsonString)
        val plants = json.getJSONArray("plants")

        // Get Recipe objects from data
        (0 until plants.length()).mapTo(recipeList) {
          Recipe(plants.getJSONObject(it).getString("title"),
                  plants.getJSONObject(it).getString("description"),
                  plants.getJSONObject(it).getString("image"),
                  plants.getJSONObject(it).getString("url"),
                  plants.getJSONObject(it).getString("dietLabel"))
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }

      return recipeList
    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
      var json: String? = null

      try {
        val inputStream = context.assets.open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charsets.UTF_8)
      } catch (ex: java.io.IOException) {
        ex.printStackTrace()
        return null
      }

      return json
    }
  }
}
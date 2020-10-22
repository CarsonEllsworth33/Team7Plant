package com.example.plant_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class HomeActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listView = findViewById<ListView>(R.id.recipe_list_view)
// 1
        val recipeList = Recipe.getRecipesFromFile("recipes.json", this)
// 2
        val listItems = arrayOfNulls<String>(recipeList.size)
// 3
        for (i in 0 until recipeList.size) {
            val recipe = recipeList[i]
            listItems[i] = recipe.title
        }
// 4
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }

}
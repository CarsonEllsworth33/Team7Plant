package com.example.plant_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
    }
    public fun goLight(view: View)
    {
        setContentView(R.layout.activity_light)
    }
}
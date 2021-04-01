/*
 * Copyright 2021 Google LLC
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

package com.google.samples.apps.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.fragment_plant_moisture_graph.*
import kotlinx.android.synthetic.main.fragment_plant_moisture_graph.view.*
import kotlinx.android.synthetic.main.fragment_plant_temperature_graph.*


class PlantAcidityGraphFragment : Fragment() {

    private val args: PlantMoistureGraphFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_plant_temperature_graph, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.plantNameText.text = args.plantId
        view.graphDataTypeText.text = "Acidity"
        val xvalue = ArrayList<String>()
        xvalue.add("Monday")
        xvalue.add("Tuesday")
        xvalue.add("Wednesday")
        xvalue.add("Thursday")
        xvalue.add("Friday")
        xvalue.add("Saturday")
        xvalue.add("Sunday")
        //Part1
        val entries = ArrayList<Entry>()

//Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))

//Part3
        val vl = LineDataSet(entries, "Acidity")
        vl.setColor(R.color.sunflower_green_500)
        vl.setCircleColor(R.color.sunflower_green_500)
        vl.fillColor = R.color.sunflower_green_300

//Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.color = R.color.sunflower_green_500
        vl.fillColor = R.color.sunflower_green_500
        vl.fillAlpha = R.color.sunflower_green_300

//Part5
//        lineChart.xAxis.labelRotationAngle = 0f

//Part6
        val data = LineData(vl)

        lineChart.data = data
//Part7
        //lineChart.axisRight.isEnabled = false
        //lineChart.xAxis.axisMaximum = j+0.1f

//Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

//Part9
        lineChart.description.text = "Days"
        lineChart.setNoDataText("No forex yet!")

//Part10
        lineChart.animateX(1800, Easing.EaseInExpo)

//Part11
        //val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
        //lineChart.marker = markerView
    }
}
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
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_plant_moisture_graph.*
import kotlinx.android.synthetic.main.fragment_plant_moisture_graph.view.*


class PlantMoistureGraphFragment : Fragment() {

    private val args: PlantMoistureGraphFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_plant_moisture_graph, container, false)


        //x axis values
        val xvalues = ArrayList<String>()
        xvalues.add("Monday")
        xvalues.add("Tuesday")
        xvalues.add("Wednesday")
        xvalues.add("Thursday")
        xvalues.add("Friday")
        xvalues.add("Saturday")
        xvalues.add("Sunday")


        // y axis values

        //bar entries
        val barentries = ArrayList<BarEntry>()

        barentries.add(BarEntry(0f, 35f))
        barentries.add(BarEntry(1f, 45f))
        barentries.add(BarEntry(2f, 25f))
        barentries.add(BarEntry(3f, 50f))
        barentries.add(BarEntry(4f, 70f))
        barentries.add(BarEntry(5f, 60f))
        barentries.add(BarEntry(6f, 80f))


        //bardata set
        var bardataset = BarDataSet(barentries, "Moisture Levels")
        bardataset.color = resources.getColor(R.color.sunflower_green_700)

        val data = BarData(bardataset)
        data.barWidth = .9f
        view.bargraph.data = data
        view.bargraph.setFitBars(true)
        view.bargraph.invalidate()

        val l: Legend = view.bargraph.getLegend()

        val leftAxis: YAxis = view.bargraph.getAxisLeft()
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
        leftAxis.setDrawLabels(true)


        view.bargraph.getAxisRight().setEnabled(false)

        val xAxis: XAxis = view.bargraph.getXAxis()
        xAxis.isEnabled = true
        xAxis.setDrawLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val description: Description = view.bargraph.getDescription()
        description.isEnabled = false



        view.bargraph.setBackgroundColor((resources.getColor(R.color.design_default_color_background)))
        view.bargraph.animateXY(3000, 3000)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.plantNameText.text = args.plantId
        view.graphDataTypeText.text = "Moisture"
    }
}
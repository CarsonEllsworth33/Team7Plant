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

package com.google.samples.apps.sunflower

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.samples.apps.sunflower.adapters.GardenPlantingAdapter
import com.google.samples.apps.sunflower.adapters.PLANT_LIST_PAGE_INDEX
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.databinding.FragmentGardenBinding
import com.google.samples.apps.sunflower.databinding.FragmentNewPlantBinding
import com.google.samples.apps.sunflower.viewmodels.GardenPlantingListViewModel
import com.google.samples.apps.sunflower.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_new_plant.*

@AndroidEntryPoint
class NewPlantFragment : Fragment() {

    private lateinit var binding: FragmentNewPlantBinding

    private val viewModel: GardenPlantingListViewModel by viewModels()

    private val plantModel: PlantListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPlantBinding.inflate(inflater, container, false)

        binding.createPlant.setOnClickListener {
            addPlantToDatabase()
            navigateToPlantListPage()
        }
        return binding.root
    }


    private fun addPlantToDatabase() {
        val plantname = new_plant_name.text.toString()
        val plantID = new_plant_sname.text.toString()
        val plant_desc = ""
        val grow_zone_num = 2
        val watering_interval = 7
        val img_url = ""
        val isPlanted = false

        if (inputCheck(plantname, plantID)){
            // Create plant Object
            val newPlant = Plant(plantID, plantname, plant_desc, grow_zone_num, watering_interval, img_url, isPlanted)
            // Add data to Database
            plantModel.addPlantToDatabase(newPlant)

        }
    }

    private fun inputCheck(plantname: String, plantID: String) : Boolean{
        return !(TextUtils.isEmpty(plantID) && TextUtils.isEmpty(plantname))
    }

    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
                PLANT_LIST_PAGE_INDEX
    }
}

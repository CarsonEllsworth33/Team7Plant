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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_custom_plant.*
import kotlinx.android.synthetic.main.fragment_custom_plant.view.*
import android.content.Intent
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.samples.apps.sunflower.adapters.PLANT_LIST_PAGE_INDEX
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.databinding.FragmentPlantDetailBinding
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CustomPlantFragment : Fragment() {

    private val args: CustomPlantFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_custom_plant, container, false)
        view.mButton.setOnClickListener {
            val direction = CustomPlantFragmentDirections.actionCustomPlantFragmentToPlantMoistureGraphFragment(args.plantId)
            findNavController().navigate(direction)
        }
        view.tButton.setOnClickListener {
            val direction = CustomPlantFragmentDirections.actionCustomPlantFragmentToPlantTemperatureGraphFragment(args.plantId)
            findNavController().navigate(direction)
        }
        view.aButton.setOnClickListener {
            val direction = CustomPlantFragmentDirections.actionCustomPlantFragmentToPlantAcidityGraphFragment(args.plantId)
            findNavController().navigate(direction)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plant_detail_name.text = args.plantId
    }
}
/*
 * Copyright 2020 Google LLC
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


//import android.R
import com.google.samples.apps.sunflower.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.samples.apps.sunflower.adapters.statBlockAdapter
import com.google.samples.apps.sunflower.data.TopSpacingItemDecoration
import com.google.samples.apps.sunflower.data.dummyStats
import com.google.samples.apps.sunflower.databinding.FragmentPlantStatsBinding
import com.google.samples.apps.sunflower.viewmodels.GardenPlantingListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_plant_stats.*


/**
 * A fragment representing a single Plant stats screen.
 */
@AndroidEntryPoint
class StatBlockFragment : Fragment(R.layout.fragment_plant_stats) {

    private lateinit var statBlockAdapter: statBlockAdapter

    private lateinit var binding: FragmentPlantStatsBinding

    private val viewModel: GardenPlantingListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantStatsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = dummyStats.createDataSet()
        statBlockAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        statsView.apply{
            layoutManager = LinearLayoutManager(this.context)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            statBlockAdapter = statBlockAdapter()
            adapter = statBlockAdapter
        }
    }
}

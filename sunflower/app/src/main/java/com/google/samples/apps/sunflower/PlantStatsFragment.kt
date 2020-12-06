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


import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import android.widget.LinearLayout
import androidx.annotation.ContentView
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.samples.apps.sunflower.adapters.statBlockAdapter
import com.google.samples.apps.sunflower.data.TopSpacingItemDecoration
import com.google.samples.apps.sunflower.data.dummyStats
import com.google.samples.apps.sunflower.databinding.FragmentNewPlantBinding
import com.google.samples.apps.sunflower.databinding.FragmentPlantStatsBinding
import com.google.samples.apps.sunflower.viewmodels.GardenPlantingListViewModel
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel
import com.google.samples.apps.sunflower.viewmodels.PlantStatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_plant_stats.*
import javax.inject.Inject


/**
 * A fragment representing a single Plant stats screen.
 */
@AndroidEntryPoint
class PlantStatsFragment : Fragment() {

    private lateinit var binding: FragmentPlantStatsBinding

    private val args: PlantStatsFragmentArgs by navArgs()

    private lateinit var statBlockAdapter: statBlockAdapter

    @Inject
    lateinit var plantStatsViewModelFactory: PlantStatsViewModel.AssistedFactory

    private val plantStatsViewModel: PlantStatsViewModel by viewModels {
        PlantStatsViewModel.provideFactory(
                plantStatsViewModelFactory,
                args.plantId
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantStatsBinding.inflate(
                inflater,
                container,
                false
        )

//        galleryNav.setOnClickListener { navigateToGallery() }

        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    createShareIntent()
                    true
                }
                else -> false
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    // Helper function for calling a share functionality.
    // Should be used when user presses a share button/menu item.
    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = plantStatsViewModel.plant.value.let { plant ->
            if (plant == null) {
                ""
            } else {
                getString(R.string.share_text_plant, plant.name)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
                .setText(shareText)
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
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

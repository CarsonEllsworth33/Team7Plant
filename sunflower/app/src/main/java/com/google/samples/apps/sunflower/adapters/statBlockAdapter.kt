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

package com.google.samples.apps.sunflower.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.statBlock
import kotlinx.android.synthetic.main.layout_statblock.view.*

class statBlockAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: List<statBlock> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return statBlockViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_statblock, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is statBlockViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(statBlockList: List<statBlock>){
        items = statBlockList
    }

    class statBlockViewHolder constructor(
            itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val statImage: ImageView = itemView.statImage
        val statTitle: TextView = itemView.statTitle
        val statData: TextView = itemView.statData

        fun bind(statBlock: statBlock){

            statTitle.text = statBlock.title
            statData.text = statBlock.data

            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(statBlock.image)
                    .into(statImage)


        }
    }
}
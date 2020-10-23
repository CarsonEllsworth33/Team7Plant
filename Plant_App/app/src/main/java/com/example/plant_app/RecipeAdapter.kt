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
//import android.support.v4.content.ContextCompat
//import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.squareup.picasso.Picasso


class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<Recipe>) : BaseAdapter() {

  private val inflater: LayoutInflater
      = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

  companion object {
    private val LABEL_COLORS = hashMapOf(
        "Low Maintenance" to R.color.colorLowCarb,
        "Medium Maintenance" to R.color.colorLowFat,
        "High Maintenance" to R.color.colorVegetarian,
        "Medium-Carb" to R.color.colorMediumCarb,
        "Vegetarian" to R.color.colorVegetarian,
        "Balanced" to R.color.colorBalanced
    )
  }

  override fun getCount(): Int {
    return dataSource.size
  }

  override fun getItem(position: Int): Any {
    return dataSource[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val view: View
    val holder: ViewHolder

    // 1
    if (convertView == null) {

      // 2
      view = inflater.inflate(R.layout.list_item_recipe, parent, false)

      // 3
      holder = ViewHolder()
      holder.thumbnailImageView = view.findViewById(R.id.recipe_list_thumbnail) as ImageView
      holder.titleTextView = view.findViewById(R.id.recipe_list_title) as TextView
      holder.subtitleTextView = view.findViewById(R.id.recipe_list_subtitle) as TextView
      holder.detailTextView = view.findViewById(R.id.recipe_list_detail) as TextView

      // 4
      view.tag = holder
    } else {
      // 5
      view = convertView
      holder = convertView.tag as ViewHolder
    }

    // 6
    val titleTextView = holder.titleTextView
    val subtitleTextView = holder.subtitleTextView
    val detailTextView = holder.detailTextView
    val thumbnailImageView = holder.thumbnailImageView

    val recipe = getItem(position) as Recipe

    titleTextView.text = recipe.title
    subtitleTextView.text = recipe.description
    detailTextView.text = recipe.label
    Picasso.get().load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

    val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
    titleTextView.typeface = titleTypeFace

    val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
    subtitleTextView.typeface = subtitleTypeFace

    val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
    detailTextView.typeface = detailTypeFace

    detailTextView.setTextColor(
        ContextCompat.getColor(context, LABEL_COLORS[recipe.label] ?: R.color.colorPrimary))

    return view
  }

  private class ViewHolder {
    lateinit var titleTextView: TextView
    lateinit var subtitleTextView: TextView
    lateinit var detailTextView: TextView
    lateinit var thumbnailImageView: ImageView
  }
}

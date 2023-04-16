package com.abdosharaf.countries

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.continents.ContinentsAdapter
import com.abdosharaf.countries.singleContinent.CountriesAdapter

@BindingAdapter("countriesNumber")
fun TextView.countriesNumber(list: MutableList<GetContinentsQuery.Country>) {
    this.text = this.context.getString(R.string.countries_num, list.size)
}

@BindingAdapter("bindContinents")
fun RecyclerView.bindContinents(list: MutableList<GetContinentsQuery.Continent>?) {
    this.isVisible = !list.isNullOrEmpty()
    (this.adapter as ContinentsAdapter).submitList(list)
}

@BindingAdapter("bindCountries")
fun RecyclerView.bindCountries(list: MutableList<GetContinentQuery.Country>?) {
    this.isVisible = !list.isNullOrEmpty()
    (this.adapter as CountriesAdapter).submitList(list)
}

@BindingAdapter("showIfNull")
fun ProgressBar.showIfNull(list: List<Any>?) {
    this.isVisible = list.isNullOrEmpty()
}

@BindingAdapter("getContinentImage")
fun ImageView.getContinentImage(code: String) {
    when(code) {
        "AF" -> this.setImageResource(R.drawable.africa)
        "AN" -> this.setImageResource(R.drawable.antarctica)
        "AS" -> this.setImageResource(R.drawable.asia)
        "EU" -> this.setImageResource(R.drawable.europe)
        "NA" -> this.setImageResource(R.drawable.north_america)
        "OC" -> this.setImageResource(R.drawable.oceania)
        "SA" -> this.setImageResource(R.drawable.south_america)
        "-1" -> this.setImageResource(R.drawable.countries)
    }
}
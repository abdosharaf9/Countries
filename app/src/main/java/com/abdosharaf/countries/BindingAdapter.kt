package com.abdosharaf.countries

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.allCountries.AllCountriesAdapter
import com.abdosharaf.countries.continents.ContinentsAdapter
import com.abdosharaf.countries.singleContinent.CountriesAdapter
import com.abdosharaf.countries.singleCountry.adapters.CurrencyAdapter
import com.abdosharaf.countries.singleCountry.adapters.LanguageAdapter
import com.abdosharaf.countries.singleCountry.adapters.StateAdapter

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

@BindingAdapter("bindAllCountries")
fun RecyclerView.bindAllCountries(list: MutableList<GetCountriesQuery.Country>?) {
    this.isVisible = !list.isNullOrEmpty()
    (this.adapter as AllCountriesAdapter).submitList(list)
}

@BindingAdapter("bindAllCurrencies")
fun RecyclerView.bindAllCurrencies(list: MutableList<String>?) {
    this.isVisible = !list.isNullOrEmpty()
    (this.adapter as CurrencyAdapter).submitList(list)
}

@BindingAdapter("bindAllLanguages")
fun RecyclerView.bindAllLanguages(list: MutableList<GetCountryQuery.Language>?) {
    this.isVisible = !list.isNullOrEmpty()
    (this.adapter as LanguageAdapter).submitList(list)
}

@BindingAdapter("bindAllStates")
fun RecyclerView.bindAllStates(list: MutableList<GetCountryQuery.State>?) {
    this.isVisible = !list.isNullOrEmpty()
    (this.adapter as StateAdapter).submitList(list)
}

@BindingAdapter("showIfNull")
fun ProgressBar.showIfNull(list: List<Any>?) {
    this.isVisible = list.isNullOrEmpty()
}

@BindingAdapter("hideIfNull")
fun View.hideIfNull(list: List<Any>?) {
    this.isVisible = !list.isNullOrEmpty()
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
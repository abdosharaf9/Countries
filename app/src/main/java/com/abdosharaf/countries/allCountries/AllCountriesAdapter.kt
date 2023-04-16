package com.abdosharaf.countries.allCountries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.GetCountriesQuery.Country
import com.abdosharaf.countries.databinding.ItemCountryForAllBinding

class AllCountriesAdapter : ListAdapter<Country, CountryViewHolder>(CountryDiffUtil) {

    var onItemClicked: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class CountryViewHolder private constructor(private val binding: ItemCountryForAllBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country, clickListener: ((Country) -> Unit)?) {
        binding.country = country
        binding.root.setOnClickListener {
            clickListener?.invoke(country)
        }
    }

    companion object {
        fun from(parent: ViewGroup): CountryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return CountryViewHolder(
                ItemCountryForAllBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return newItem.code == oldItem.code
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return newItem == oldItem
    }
}
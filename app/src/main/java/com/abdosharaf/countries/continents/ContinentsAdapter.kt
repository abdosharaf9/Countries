package com.abdosharaf.countries.continents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.GetContinentsQuery.Country
import com.abdosharaf.countries.GetContinentsQuery.Continent
import com.abdosharaf.countries.databinding.ItemContinentBinding

class ContinentsAdapter : ListAdapter<Continent, ContinentViewHolder>(ContinentDiffUtil) {

    var onItemClicked: ((Continent) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentViewHolder {
        return ContinentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ContinentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }

    override fun submitList(list: MutableList<Continent>?) {
        list?.let { continents ->
            val allCountries: MutableList<Country> = mutableListOf()
            continents.forEach { continent ->
                allCountries.addAll(continent.countries)
            }
            val modifiedList = continents.map { it.copy() }.toMutableList()
            modifiedList.add(Continent("-1", "All the world", allCountries))
            super.submitList(modifiedList)
        }
    }
}

class ContinentViewHolder private constructor(private val binding: ItemContinentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(Continent: Continent, clickListener: ((Continent) -> Unit)?) {
        binding.continent = Continent
        binding.root.setOnClickListener {
            clickListener?.invoke(Continent)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ContinentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ContinentViewHolder(
                ItemContinentBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object ContinentDiffUtil : DiffUtil.ItemCallback<Continent>() {
    override fun areItemsTheSame(oldItem: Continent, newItem: Continent): Boolean {
        return newItem.code == oldItem.code
    }

    override fun areContentsTheSame(oldItem: Continent, newItem: Continent): Boolean {
        return newItem == oldItem
    }
}
package com.abdosharaf.countries.singleCountry.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.databinding.ItemCurrencyBinding

class CurrencyAdapter : ListAdapter<String, CurrencyViewHolder>(CurrencyDiffUtil) {

    var onItemClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class CurrencyViewHolder private constructor(private val binding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: String, clickListener: ((String) -> Unit)?) {
        binding.currency = currency
        binding.root.setOnClickListener { clickListener?.invoke(currency) }
    }

    companion object {
        fun from(parent: ViewGroup): CurrencyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return CurrencyViewHolder(
                ItemCurrencyBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object CurrencyDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem === oldItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return newItem == oldItem
    }
}
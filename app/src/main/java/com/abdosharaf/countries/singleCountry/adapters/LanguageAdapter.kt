package com.abdosharaf.countries.singleCountry.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.GetCountryQuery.Language
import com.abdosharaf.countries.databinding.ItemLanguageBinding

class LanguageAdapter : ListAdapter<Language, LanguageViewHolder>(LanguageDiffUtil) {

    var onItemClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        return LanguageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class LanguageViewHolder private constructor(private val binding: ItemLanguageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(language: Language, clickListener: ((String) -> Unit)?) {
        binding.language = language
        val toastText = "${language.name} - ${language.native}"
        binding.root.setOnClickListener {
            clickListener?.invoke(toastText)
        }
    }

    companion object {
        fun from(parent: ViewGroup): LanguageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return LanguageViewHolder(
                ItemLanguageBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object LanguageDiffUtil : DiffUtil.ItemCallback<Language>() {
    override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
        return newItem.code == oldItem.code
    }

    override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
        return newItem == oldItem
    }
}
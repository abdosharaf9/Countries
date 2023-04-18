package com.abdosharaf.countries.singleCountry.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.countries.GetCountryQuery.State
import com.abdosharaf.countries.databinding.ItemStateBinding

class StateAdapter : ListAdapter<State, StateViewHolder>(StateDiffUtil) {

    var onItemClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class StateViewHolder private constructor(private val binding: ItemStateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(state: State, clickListener: ((String) -> Unit)?) {
        binding.state = state
        binding.root.setOnClickListener { clickListener?.invoke(state.name) }
    }

    companion object {
        fun from(parent: ViewGroup): StateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return StateViewHolder(
                ItemStateBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object StateDiffUtil : DiffUtil.ItemCallback<State>() {
    override fun areItemsTheSame(oldItem: State, newItem: State): Boolean {
        return newItem.code == oldItem.code
    }

    override fun areContentsTheSame(oldItem: State, newItem: State): Boolean {
        return newItem == oldItem
    }
}
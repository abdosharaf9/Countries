package com.abdosharaf.countries.continents

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.countries.ApiService.apolloClient
import com.abdosharaf.countries.Constants.TAG
import com.abdosharaf.countries.GetContinentsQuery.Continent
import com.abdosharaf.countries.GetContinentsQuery
import kotlinx.coroutines.launch

class ContinentsViewModel : ViewModel() {

    private val _continents = MutableLiveData<List<Continent>>()
    val continents: LiveData<List<Continent>>
        get() = _continents

    init {
        getContinents()
    }

    private fun getContinents() {
        try {
            viewModelScope.launch {
                _continents.value =
                    apolloClient.query(GetContinentsQuery()).execute().data?.continents
            }
        } catch (e: Exception) {
            Log.d(TAG, "getContinents: ${e.message}")
        }
    }
}
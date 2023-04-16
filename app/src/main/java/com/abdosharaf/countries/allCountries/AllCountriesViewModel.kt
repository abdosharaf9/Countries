package com.abdosharaf.countries.allCountries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.countries.ApiService.apolloClient
import com.abdosharaf.countries.Constants.TAG
import com.abdosharaf.countries.GetCountriesQuery
import com.abdosharaf.countries.GetCountriesQuery.Country
import kotlinx.coroutines.launch

class AllCountriesViewModel: ViewModel() {

    private val _world = MutableLiveData<List<Country>?>()
    val world: LiveData<List<Country>?>
        get() = _world

    fun getAllCountries() {
        try {
            viewModelScope.launch {
                _world.value =
                    apolloClient.query(GetCountriesQuery()).execute().data?.countries
            }
        } catch (e: Exception) {
            Log.e(TAG, "getContinent: ${e.message}")
        }
    }
}
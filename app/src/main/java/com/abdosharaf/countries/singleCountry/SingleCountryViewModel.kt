package com.abdosharaf.countries.singleCountry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.countries.ApiService.apolloClient
import com.abdosharaf.countries.Constants.TAG
import com.abdosharaf.countries.GetCountryQuery.Country
import com.abdosharaf.countries.GetCountryQuery
import kotlinx.coroutines.launch

class SingleCountryViewModel : ViewModel() {

    private val _country = MutableLiveData<Country?>()
    val country: LiveData<Country?>
        get() = _country

    fun getCountry(code: String) {
        try {
            viewModelScope.launch {
                _country.value = apolloClient.query(GetCountryQuery(code)).execute().data?.country
            }
        } catch (e: Exception) {
            Log.d(TAG, "getCountry: ${e.message}")
        }
    }
}
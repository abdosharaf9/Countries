package com.abdosharaf.countries.singleContinent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.countries.ApiService.apolloClient
import com.abdosharaf.countries.Constants.TAG
import com.abdosharaf.countries.GetContinentQuery
import com.abdosharaf.countries.GetContinentQuery.Continent
import kotlinx.coroutines.launch

class SingleContinentViewModel: ViewModel() {

    private val _continent = MutableLiveData<Continent?>()
    val continent: LiveData<Continent?>
        get() = _continent

    fun getContinent(code: String) {
        try {
            viewModelScope.launch {
                _continent.value =
                    apolloClient.query(GetContinentQuery(code)).execute().data?.continent
            }
        } catch (e: Exception) {
            Log.e(TAG, "getContinent: ${e.message}")
        }
    }
}
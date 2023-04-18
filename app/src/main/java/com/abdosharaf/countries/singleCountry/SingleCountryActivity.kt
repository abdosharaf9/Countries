package com.abdosharaf.countries.singleCountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.abdosharaf.countries.databinding.ActivitySingleCountryBinding
import com.abdosharaf.countries.singleCountry.adapters.CurrencyAdapter
import com.abdosharaf.countries.singleCountry.adapters.LanguageAdapter
import com.abdosharaf.countries.singleCountry.adapters.StateAdapter

class SingleCountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleCountryBinding
    private val viewModel: SingleCountryViewModel by viewModels()
    private val currenciesAdapter = CurrencyAdapter()
    private val languagesAdapter = LanguageAdapter()
    private val statesAdapter = StateAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val code = intent.getStringExtra("code")
        viewModel.getCountry(code!!)

        languagesAdapter.onItemClicked = { text: String ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        currenciesAdapter.onItemClicked = { text: String ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        statesAdapter.onItemClicked = { text: String ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        binding.rvCountryCurrencies.adapter = currenciesAdapter
        binding.rvCountryLanguages.adapter = languagesAdapter
        binding.rvCountryStates.adapter = statesAdapter

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
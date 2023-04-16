package com.abdosharaf.countries.singleContinent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.abdosharaf.countries.R
import com.abdosharaf.countries.databinding.ActivitySingleContinentBinding
import com.abdosharaf.countries.singleCountry.SingleCountryActivity

class SingleContinentActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleContinentBinding
    private val viewModel: SingleContinentViewModel by viewModels()
    private val adapter = CountriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleContinentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("name")
        val code = intent.getStringExtra("code")
        val count = intent.getIntExtra("count", 0)
        binding.tvContinentTitle.text = getString(R.string.continent_title, title, count)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.rvCountries.adapter = adapter
        adapter.onItemClicked = { country ->
            Intent(this, SingleCountryActivity::class.java).also { intent ->
                intent.putExtra("code", country.code)
                startActivity(intent)
            }
        }

        viewModel.getContinent(code!!)
    }
}
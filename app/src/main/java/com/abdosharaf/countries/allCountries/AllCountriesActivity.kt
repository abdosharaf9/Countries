package com.abdosharaf.countries.allCountries

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.abdosharaf.countries.R
import com.abdosharaf.countries.databinding.ActivityAllCountriesBinding
import com.abdosharaf.countries.singleCountry.SingleCountryActivity

class AllCountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllCountriesBinding
    private val viewModel: AllCountriesViewModel by viewModels()
    private val adapter = AllCountriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("name")
        val count = intent.getIntExtra("count", 0)
        binding.tvWorldTitle.text = getString(R.string.continent_title, title, count)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.rvCountries.adapter = adapter
        adapter.onItemClicked = { country ->
            val myIntent = Intent(this, SingleCountryActivity::class.java)
            myIntent.putExtra("code", country.code)

            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            startActivity(myIntent, options.toBundle())
        }

        viewModel.getAllCountries()
    }
}
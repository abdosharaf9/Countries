package com.abdosharaf.countries.continents

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.abdosharaf.countries.R
import com.abdosharaf.countries.allCountries.AllCountriesActivity
import com.abdosharaf.countries.databinding.ActivityContinentsBinding
import com.abdosharaf.countries.singleContinent.SingleContinentActivity

class ContinentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContinentsBinding
    private val viewModel: ContinentsViewModel by viewModels()
    private val adapter = ContinentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContinentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.onItemClicked = { continent ->
            if(continent.code != "-1") {
                val myIntent = Intent(this, SingleContinentActivity::class.java)
                myIntent.putExtra("code", continent.code)
                myIntent.putExtra("name", continent.name)
                myIntent.putExtra("count", continent.countries.size)

                val options = ActivityOptions.makeCustomAnimation(
                    this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
                startActivity(myIntent, options.toBundle())
            } else {
                val myIntent = Intent(this, AllCountriesActivity::class.java)
                myIntent.putExtra("name", continent.name)
                myIntent.putExtra("count", continent.countries.size)

                val options = ActivityOptions.makeCustomAnimation(
                    this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
                startActivity(myIntent, options.toBundle())
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvContinents.adapter = adapter
    }
}
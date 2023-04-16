package com.abdosharaf.countries.singleCountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.abdosharaf.countries.databinding.ActivitySingleCountryBinding

class SingleCountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleCountryBinding
    private val viewModel: SingleCountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val code = intent.getStringExtra("code")
        viewModel.getCountry(code!!)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
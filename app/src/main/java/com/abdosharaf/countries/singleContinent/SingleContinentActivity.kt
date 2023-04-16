package com.abdosharaf.countries.singleContinent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.abdosharaf.countries.R
import com.abdosharaf.countries.databinding.ActivitySingleContinentBinding

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
            Toast.makeText(this, country.name, Toast.LENGTH_SHORT).show()
        }

        viewModel.getContinent(code!!)
    }
}
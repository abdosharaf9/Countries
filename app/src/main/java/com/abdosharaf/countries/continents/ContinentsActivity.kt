package com.abdosharaf.countries.continents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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
                Intent(this, SingleContinentActivity::class.java).also { intent ->
                    intent.putExtra("code", continent.code)
                    intent.putExtra("name", continent.name)
                    intent.putExtra("count", continent.countries.size)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Not implemented yet!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvContinents.adapter = adapter
    }
}
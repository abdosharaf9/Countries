package com.abdosharaf.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import androidx.lifecycle.lifecycleScope
import com.abdosharaf.countries.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql/")
            .okHttpClient(okHttpClient)
            .build()

        lifecycleScope.launch {
            val response = apolloClient.query(GetCountryQuery("EG")).execute()
            val country = response.data?.country
            country?.let {
                binding.textView.text = getString(R.string.country, it.name, it.native, it.capital, it.languages[0].name, it.languages[0].native, it.emoji)
            }
        }
    }
}
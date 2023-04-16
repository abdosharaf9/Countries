package com.abdosharaf.countries

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiService {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql/")
        .okHttpClient(okHttpClient)
        .build()
}
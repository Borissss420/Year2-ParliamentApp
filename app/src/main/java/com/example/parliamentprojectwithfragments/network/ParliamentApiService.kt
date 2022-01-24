package com.example.parliamentprojectwithfragments.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//Api service

private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParliamentApiService {
    @GET("mps.json")
    suspend fun getProperties(): List<ParliamentProperty>
}

object ParliamentApi {
    val retrofitService: ParliamentApiService by lazy {
        retrofit.create(ParliamentApiService::class.java)
    }
}
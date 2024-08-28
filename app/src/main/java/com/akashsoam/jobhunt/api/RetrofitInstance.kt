package com.akashsoam.jobhunt.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://testapi.getlokalapp.com/"

    val api: JobsApiServiceInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobsApiServiceInterface::class.java)
    }
}

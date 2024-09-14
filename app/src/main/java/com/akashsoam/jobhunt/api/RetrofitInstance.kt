package com.akashsoam.jobhunt.api

import com.akashsoam.jobhunt.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: JobsApiServiceInterface by lazy {
        retrofit.create(JobsApiServiceInterface::class.java)
    }
}

package com.akashsoam.jobhunt.api

import com.akashsoam.jobhunt.models.JobResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApiServiceInterface {

    @GET("common/jobs")
//    @GET
//    suspend fun getJobs(@Query("page") page: Int): Response<JobResponse>
    suspend fun getJobs(@Query("page") page: Int): JobResponse
}
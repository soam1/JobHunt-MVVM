package com.akashsoam.jobhunt.api

import com.akashsoam.jobhunt.models.JobsResponse
import retrofit2.Response
import retrofit2.http.GET

interface JobsApiInterface {

//    @GET("/jobs?page=1")
    @GET
    suspend fun getJobs(): Response<JobsResponse>
}
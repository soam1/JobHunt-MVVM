package com.akashsoam.jobhunt.api

import com.akashsoam.jobhunt.models.JobsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApiServiceInterface {
    @GET("common/jobs")
    suspend fun getJobs(
        @Query("page") page: Int
    ): Response<JobsResponse>
}

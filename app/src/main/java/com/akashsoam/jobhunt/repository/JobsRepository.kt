package com.akashsoam.jobhunt.repository

import android.app.Application
import android.util.Log
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.db.JobDatabase
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job

class JobsRepository(application: Application) {

    private val jobDao = JobDatabase.getDatabase(application).jobDao()
    private val jobsApiService = RetrofitInstance.api

    suspend fun getJobsFromApi(page: Int): List<Job> {
        return try {
            val response = jobsApiService.getJobs(page)
            if (response.isSuccessful) {
                val jobs = response.body()?.results ?: emptyList()
                Log.d("JobsRepository", "Fetched jobs: $jobs")
                jobs
            } else {
                Log.e("JobsRepository", "API call failed: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("JobsRepository", "Exception during API call", e)
            emptyList()
        }
    }


    suspend fun saveJob(jobEntity: JobEntity) {
        jobDao.insert(jobEntity)
    }

    fun getBookmarkedJobs() = jobDao.getAllJobs()

    suspend fun deleteJob(jobId: Int) {
        jobDao.deleteJob(jobId)
    }
}

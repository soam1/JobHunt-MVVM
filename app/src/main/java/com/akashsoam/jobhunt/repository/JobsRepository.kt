package com.akashsoam.jobhunt.repository

import android.app.Application
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.db.JobDatabase
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job

class JobsRepository(application: Application) {

    private val jobDao = JobDatabase.getDatabase(application).jobDao()
    private val jobsApiService = RetrofitInstance.api

    suspend fun getJobsFromApi(page: Int): List<Job> {
        val response = jobsApiService.getJobs(page)
        return if (response.isSuccessful) {
            response.body()?.jobs ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun saveJob(jobEntity: JobEntity) {
        jobDao.insert(jobEntity)
    }

    fun getBookmarkedJobs() = jobDao.getAllJobs()

    suspend fun deleteJob(jobId : Int) {
        jobDao.deleteJob(jobId)
    }
}

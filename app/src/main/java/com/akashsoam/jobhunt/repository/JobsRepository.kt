package com.akashsoam.jobhunt.repository

import android.app.Application
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.db.JobDatabase
import com.akashsoam.jobhunt.db.JobEntity

class JobsRepository(application: Application) {

    private val jobDao = JobDatabase.getDatabase(application).jobDao()
    private val jobsApiService = RetrofitInstance.api

    suspend fun getJobsFromApi(page: Int): List<JobEntity> {
        val response = jobsApiService.getJobs(page)
        return if (response.isSuccessful) {
            response.body()?.jobs?.map { job ->
                JobEntity(
                    id = job.id,
                    title = job.title,
                    location = job.location,
                    salary = job.salary,
                    phone = job.phone
                )
            } ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun saveJob(job: JobEntity) {
        jobDao.insert(job)
    }

    fun getBookmarkedJobs() = jobDao.getAllJobs()

    suspend fun deleteJob(jobId: String) {
        jobDao.deleteJob(jobId)
    }
}

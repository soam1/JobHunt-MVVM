package com.akashsoam.jobhunt.repository

import androidx.lifecycle.LiveData
import com.akashsoam.jobhunt.api.JobsApiServiceInterface
import com.akashsoam.jobhunt.db.JobDao
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job

class JobRepository(
    private val api: JobsApiServiceInterface,
    private val jobDao: JobDao
) {

    suspend fun getJobsFromApi(page: Int): List<Job> {
        val response = api.getJobs(page)
        return response.jobs
    }

    suspend fun bookmarkJob(job: JobEntity) {
        jobDao.bookmarkJob(job)
    }

    suspend fun removeJob(job: JobEntity) {
        jobDao.removeJob(job)
    }

    fun getAllBookmarkedJobs(): LiveData<List<JobEntity>> {
        return jobDao.getAllBookmarkedJobs()
    }
}

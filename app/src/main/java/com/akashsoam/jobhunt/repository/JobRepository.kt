package com.akashsoam.jobhunt.repository

import androidx.lifecycle.LiveData
import com.akashsoam.jobhunt.api.JobsApiServiceInterface
import com.akashsoam.jobhunt.db.JobDao
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.JobResponse

class JobRepository(private val jobDao: JobDao, private val apiService: JobsApiServiceInterface) {

    val bookmarkedJobs: LiveData<List<JobEntity>> = jobDao.getBookmarkedJobs()

    suspend fun fetchJobs(page: Int): JobResponse {
        return apiService.getJobs(page)
    }

    suspend fun bookmarkJob(job: JobEntity) {
        jobDao.insertJob(job)
    }

    suspend fun removeBookmark(job: JobEntity) {
        jobDao.deleteJob(job)
    }
}

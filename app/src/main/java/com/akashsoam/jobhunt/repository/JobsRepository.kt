package com.akashsoam.jobhunt.repository

import androidx.lifecycle.LiveData
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.database.JobDao
import com.akashsoam.jobhunt.model.Job

class JobRepository(private val jobDao: JobDao) {

    val allJobs: LiveData<List<Job>> = jobDao.getAllJobs()

    suspend fun insertJob(job: Job) {
        jobDao.insertJob(job)
    }

    suspend fun deleteJob(job: Job) {
        jobDao.deleteJob(job)
    }

    suspend fun getJobs(page: Int): List<com.akashsoam.jobhunt.models.Job> {
        return RetrofitInstance.api.getJobs(page)
    }
}

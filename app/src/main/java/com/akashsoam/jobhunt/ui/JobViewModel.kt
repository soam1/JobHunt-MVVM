package com.akashsoam.jobhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job
import com.akashsoam.jobhunt.repository.JobRepository
import kotlinx.coroutines.launch

class JobViewModel(private val repository: JobRepository) : ViewModel() {

    private val _jobs = MutableLiveData<List<Job>>()
    val jobs: LiveData<List<Job>> get() = _jobs

    fun getJobs(page: Int) {
        viewModelScope.launch {
            _jobs.value = repository.getJobsFromApi(page)
        }
    }

    fun bookmarkJob(job: JobEntity) {
        viewModelScope.launch {
            repository.bookmarkJob(job)
        }
    }

    fun removeJob(job: JobEntity) {
        viewModelScope.launch {
            repository.removeJob(job)
        }
    }

    val bookmarkedJobs: LiveData<List<JobEntity>> = repository.getAllBookmarkedJobs()
}

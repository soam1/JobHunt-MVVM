package com.akashsoam.jobhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.models.Job
import com.akashsoam.jobhunt.repository.JobRepository
import kotlinx.coroutines.launch

class JobsViewModel(private val repository: JobRepository) : ViewModel() {

    val allJobs: LiveData<List<Job>> = repository.allJobs
    private val _jobs = MutableLiveData<List<Job>>()
    val jobs: LiveData<List<Job>> = _jobs

    fun insertJob(job: Job) = viewModelScope.launch {
        repository.insertJob(job)
    }

    fun deleteJob(job: Job) = viewModelScope.launch {
        repository.deleteJob(job)
    }

    fun getJobs(page: Int) = viewModelScope.launch {
        val jobList = repository.getJobs(page)
        _jobs.postValue(jobList)
    }
}

//class JobsViewModelFactory(private val repository: JobRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(JobsViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return JobsViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

package com.akashsoam.jobhunt.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.repository.JobsRepository
import com.akashsoam.jobhunt.utils.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobsRepository(application)
    private val _jobs = MutableLiveData<List<JobEntity>>()
    val jobs: LiveData<List<JobEntity>> get() = _jobs

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private val currentJobs = mutableListOf<JobEntity>()

    fun loadJobs(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingState.postValue(LoadingState.LOADING)
            val result = repository.getJobsFromApi(page)
            if (result.isNotEmpty()) {
                currentJobs.addAll(result)
                _jobs.postValue(currentJobs)
                _loadingState.postValue(LoadingState.SUCCESS)
            } else if (page == 1) {
                _loadingState.postValue(LoadingState.EMPTY)
            } else {
                _loadingState.postValue(LoadingState.ERROR)
            }
        }
    }

    fun bookmarkJob(job: JobEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveJob(job)
        }
    }

    fun getBookmarkedJobs() = repository.getBookmarkedJobs()

    fun deleteJob(jobId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteJob(jobId)
        }
    }
}

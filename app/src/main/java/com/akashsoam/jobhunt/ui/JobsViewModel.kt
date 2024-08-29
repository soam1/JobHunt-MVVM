package com.akashsoam.jobhunt.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job
import com.akashsoam.jobhunt.repository.JobsRepository
import com.akashsoam.jobhunt.util.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = JobsRepository(application)
    private val _jobs = MutableLiveData<List<Job>>()
    val jobs: LiveData<List<Job>> get() = _jobs

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private val currentJobs = mutableListOf<Job>()

    fun loadJobs(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingState.postValue(LoadingState.LOADING)
            val result = repository.getJobsFromApi(page)
            Log.d("JobsViewModel", "page in viewmodel is $page")

            Log.d("JobsViewModel", "loadJobs: $result")
            if (result.isNotEmpty()) {
//                Log.d("JobsViewModel", "loadJobs: $result")
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

    fun bookmarkJob(job: Job) {
        viewModelScope.launch(Dispatchers.IO) {
            val jobEntity = JobEntity(
                id = job.id,
                title = job.title,
                company_name = job.company_name,
                place = if (job.primary_details != null) {
                    job.primary_details.Place
                } else {
                    "Location not available"
                },
//                        place = job . primary_details . Place,
                salary = if (job.primary_details != null) {
                    job.primary_details.Salary
                } else {
                    "Salary not available"
                },
//                salary = job.primary_details.Salary,
                whatsapp_no = job.whatsapp_no,
                updated_on = job.updated_on

            )
            job.is_bookmarked = true
            repository.saveJob(jobEntity)
        }
    }

    fun getBookmarkedJobs() = repository.getBookmarkedJobs()

    fun deleteJob(jobId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteJob(jobId)
        }
    }
}

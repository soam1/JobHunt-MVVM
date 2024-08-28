package com.akashsoam.jobhunt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.models.Job
import kotlinx.coroutines.launch

class JobsViewModel : ViewModel() {
    val jobsLiveData = MutableLiveData<List<Job>>()
    val errorLiveData = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchJobs(page: Int = 1) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getJobs(page)
                jobsLiveData.value = response.results
                isLoading.value = false
            } catch (e: Exception) {
                errorLiveData.value = e.message
                isLoading.value = false
            }
        }
    }
}

package com.akashsoam.jobhunt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akashsoam.jobhunt.repository.JobsRepository
import com.akashsoam.jobhunt.viewmodel.JobsViewModel

class JobsViewModelFactory(private val repository: JobsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


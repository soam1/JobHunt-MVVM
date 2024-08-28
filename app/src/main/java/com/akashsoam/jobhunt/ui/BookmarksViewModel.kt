package com.akashsoam.jobhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.repository.JobRepository
import kotlinx.coroutines.launch

class BookmarksViewModel(private val repository: JobRepository) : ViewModel() {

    val bookmarkedJobs: LiveData<List<JobEntity>> = repository.getAllBookmarkedJobs()

    fun removeBookmark(job: JobEntity) {
        viewModelScope.launch {
            repository.removeJob(job)
        }
    }
}

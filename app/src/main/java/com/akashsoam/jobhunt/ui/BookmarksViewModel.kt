package com.akashsoam.jobhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.repository.JobsRepository
import kotlinx.coroutines.launch

class BookmarksViewModel(private val repository: JobsRepository) : ViewModel() {

    val bookmarkedJobs: LiveData<List<JobEntity>> = repository.getBookmarkedJobs()

    fun removeBookmark(jobId: Int) {
        viewModelScope.launch {
            repository.deleteJob(jobId)
        }
    }
}

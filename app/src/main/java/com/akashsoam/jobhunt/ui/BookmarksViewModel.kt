package com.akashsoam.jobhunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashsoam.jobhunt.db.Job
import com.akashsoam.jobhunt.repository.JobsRepository
import kotlinx.coroutines.launch

class BookmarksViewModel(private val repository: JobsRepository) : ViewModel() {

    val bookmarkedJobs: LiveData<List<Job>> = repository.getBookmarkedJobs

    fun removeBookmark(job: Job) {
        viewModelScope.launch {
            repository.removeBookmark(job)
        }
    }
}

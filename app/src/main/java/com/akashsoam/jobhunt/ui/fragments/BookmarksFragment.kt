package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashsoam.jobhunt.R
import com.akashsoam.jobhunt.adapters.JobsAdapter
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.databinding.FragmentBookmarksBinding
import com.akashsoam.jobhunt.db.Job
import com.akashsoam.jobhunt.repository.JobsRepository
import com.akashsoam.jobhunt.ui.BookmarksViewModel
import com.akashsoam.jobhunt.ui.BookmarksViewModelFactory
import com.akashsoam.jobhunt.util.JobDatabaseProvider

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {
    private lateinit var binding: FragmentBookmarksBinding
    private val viewModel: BookmarksViewModel by viewModels {
        BookmarksViewModelFactory(
            JobsRepository(
                JobDatabaseProvider.provideJobDao(requireContext()),
                RetrofitInstance.api, // Using your existing Retrofit instance
            )
        )
    }
    private val jobsAdapter = JobsAdapter(::onJobClicked, ::onRemoveBookmarkClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeViewModel()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jobsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.bookmarkedJobs.observe(viewLifecycleOwner) {
            jobsAdapter.submitList(it)
        }
    }

    private fun onJobClicked(job: Job) {
        // Navigate to JobDetailsFragment
    }

    private fun onRemoveBookmarkClicked(job: Job) {
        viewModel.removeBookmark(job)
    }
}
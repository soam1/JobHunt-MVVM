package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.adapters.JobAdapter
import com.akashsoam.jobhunt.databinding.FragmentJobsBinding
import com.akashsoam.jobhunt.ui.JobsViewModel
import com.akashsoam.jobhunt.util.LoadingState
import com.google.android.material.snackbar.Snackbar

class JobsFragment : Fragment() {

    private var _binding: FragmentJobsBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobsViewModel: JobsViewModel
    private lateinit var jobsAdapter: JobAdapter
    private var currentPage = 1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsViewModel = ViewModelProvider(this).get(JobsViewModel::class.java)
        setupRecyclerView()
        observeViewModel()
        loadNextPage()  // Load the first page
    }

    private fun setupRecyclerView() {
        jobsAdapter = JobAdapter(onClick = { job ->
            val action = JobsFragmentDirections.actionJobsFragmentToJobDetailFragment(job)
            findNavController().navigate(action)
        }, bookmarkClickListener = { job ->
            jobsViewModel.bookmarkJob(job)
            jobsAdapter.notifyDataSetChanged()
            Snackbar.make(binding.root, "Job bookmarked", Snackbar.LENGTH_SHORT).setAction("Undo") {
                jobsViewModel.deleteJob(job.id)
                job.is_bookmarked = false
                jobsAdapter.notifyDataSetChanged()
            }.show()
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = jobsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading && totalItemCount <= lastVisibleItem + 5) {
                        loadNextPage()
                    }
                }
            })
        }
    }

    private fun observeViewModel() {
        jobsViewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            jobsAdapter.submitList(jobs)
            isLoading = false
        }

        jobsViewModel.loadingState.observe(viewLifecycleOwner) { state ->
            when (state) {
                LoadingState.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorLayout.visibility = View.GONE
                    Log.d("JobsFragment", "Loading jobs for page: $currentPage")
                }

                LoadingState.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.errorLayout.visibility = View.GONE
                }

                LoadingState.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.retryButton.setOnClickListener {
                        jobsViewModel.loadJobs(currentPage)
                    }
                }

                LoadingState.EMPTY -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorLayout.apply {
                        visibility = View.VISIBLE
                        binding.errorMessage.text = "No jobs available at the moment."
                    }
                }

                null -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorLayout.apply {
                        visibility = View.VISIBLE
                        binding.errorMessage.text = "Unexpected error. Please try again."
                    }
                }
            }
        }
    }
    

    private fun loadNextPage() {
        isLoading = true
        jobsViewModel.loadJobs(currentPage)
        currentPage++
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

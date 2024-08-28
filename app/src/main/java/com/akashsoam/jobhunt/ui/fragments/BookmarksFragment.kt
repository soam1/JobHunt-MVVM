package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashsoam.jobhunt.adapters.JobAdapter
import com.akashsoam.jobhunt.databinding.FragmentBookmarksBinding
import com.akashsoam.jobhunt.ui.viewmodel.JobsViewModel

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobsViewModel: JobsViewModel
    private lateinit var bookmarksAdapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsViewModel = ViewModelProvider(this).get(JobsViewModel::class.java)
        bookmarksAdapter = JobAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarksAdapter
        }

        jobsViewModel.getBookmarkedJobs().observe(viewLifecycleOwner) { jobs ->
            bookmarksAdapter.submitList(jobs)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

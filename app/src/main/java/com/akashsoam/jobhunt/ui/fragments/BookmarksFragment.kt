package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashsoam.jobhunt.adapters.JobAdapter
import com.akashsoam.jobhunt.api.RetrofitInstance
import com.akashsoam.jobhunt.database.JobDatabase
import com.akashsoam.jobhunt.databinding.FragmentBookmarksBinding
import com.akashsoam.jobhunt.repository.JobRepository
import com.akashsoam.jobhunt.ui.JobViewModel
import com.akashsoam.jobhunt.ui.JobsViewModelFactory

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private val jobsViewModel: JobViewModel by viewModels {
        JobsViewModelFactory(
            JobRepository(
                RetrofitInstance.api,
                JobDatabase.getDatabase(requireContext()).jobDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jobAdapter = JobAdapter()
        binding.recyclerView.apply {
            adapter = jobAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        jobsViewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            jobAdapter.submitList(jobs)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akashsoam.jobhunt.adapters.JobAdapter
import com.akashsoam.jobhunt.databinding.FragmentJobsBinding
import com.akashsoam.jobhunt.ui.JobViewModel

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private val viewModel: JobViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = JobAdapter { job ->

        }
        binding.recyclerView.adapter = adapter

        viewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            adapter.submitList(jobs)
        }

        viewModel.getJobs(1)
    }
}


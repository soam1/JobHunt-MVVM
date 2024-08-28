package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akashsoam.jobhunt.adapters.JobAdapter
import com.akashsoam.jobhunt.databinding.FragmentJobsBinding
import com.akashsoam.jobhunt.db.JobEntity
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

        val jobAdapter = JobAdapter { jobEntity ->
            val action = JobsFragmentDirections.actionJobsFragmentToJobDetailFragment(jobEntity)
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = jobAdapter

        viewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            val jobEntities = jobs.map { job ->
                JobEntity(
                    id = job.id,
                    title = job.title,
                    location = job.location,
                    salary = job.salary,
                    phone = job.phone
                    // Map other fields as necessary
                )
            }
            jobAdapter.submitList(jobEntities)
        }

        viewModel.getJobs(1)
    }
}

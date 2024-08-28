package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashsoam.jobhunt.adapter.JobsAdapter
import com.akashsoam.jobhunt.databinding.FragmentJobsBinding
import com.akashsoam.jobhunt.viewmodel.JobsViewModel

class JobsFragment : Fragment() {

    private var _binding: FragmentJobsBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobsViewModel: JobsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsViewModel = ViewModelProvider(this).get(JobsViewModel::class.java)
        val adapter = JobsAdapter { job ->
            // Handle job item click, navigate to JobDetailFragment
            val action = JobsFragmentDirections.actionJobsFragmentToJobDetailFragment(job)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        jobsViewModel.jobsLiveData.observe(viewLifecycleOwner, { jobs ->
            adapter.submitList(jobs)
        })

        jobsViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        jobsViewModel.errorLiveData.observe(viewLifecycleOwner, { errorMessage ->
            // Handle errors
        })

        jobsViewModel.fetchJobs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

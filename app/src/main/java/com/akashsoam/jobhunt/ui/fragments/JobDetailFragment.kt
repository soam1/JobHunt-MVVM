package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akashsoam.jobhunt.databinding.FragmentJobDetailBinding
import com.akashsoam.jobhunt.models.Job
import com.akashsoam.jobhunt.ui.viewmodel.JobsViewModel
import com.google.android.material.snackbar.Snackbar

class JobDetailFragment : Fragment() {

    private var _binding: FragmentJobDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobsViewModel: JobsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsViewModel = ViewModelProvider(this).get(JobsViewModel::class.java)

        val job = arguments?.let { JobDetailFragmentArgs.fromBundle(it).job }

        job?.let { jobDetails: Job ->  // Specify the type explicitly
            binding.apply {
                title.text = jobDetails.title
                location.text = jobDetails.primary_details?.Place
                salary.text = jobDetails.primary_details?.Salary
                phone.text = jobDetails.whatsapp_no
                bookmarkButton.setOnClickListener {
                    jobsViewModel.bookmarkJob(jobDetails)
//                    Toast.makeText(context, "Job bookmarked", Toast.LENGTH_SHORT).show()
                    Snackbar.make(view, "Job bookmarked", Snackbar.LENGTH_SHORT).show()
                }
            }
        } ?: run {
            // Handle the case where the job is null (optional)
            binding.title.text = "Job details unavailable"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

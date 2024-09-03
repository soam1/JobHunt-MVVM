package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akashsoam.jobhunt.R
import com.akashsoam.jobhunt.databinding.FragmentJobDetailBinding
import com.akashsoam.jobhunt.models.Job
import com.akashsoam.jobhunt.ui.JobsViewModel
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
                bookmarkButton.setImageResource(
                    if (jobDetails.is_bookmarked) {
                        R.drawable.ic_bookmark_fill
                    } else {
                        R.drawable.ic_bookmark_border
                    }
                )
                bookmarkButton.setOnClickListener {
                    if (jobDetails.is_bookmarked) {
                        jobDetails.is_bookmarked = false
                        jobsViewModel.deleteJob(jobDetails.id)
                        Snackbar.make(view, "Job removed from bookmarks", Snackbar.LENGTH_SHORT)
                            .setAction("Undo") {
                                jobsViewModel.bookmarkJob(jobDetails)
                                jobDetails.is_bookmarked = true
                            }.show()
                        bookmarkButton.setImageResource(R.drawable.ic_bookmark_border)
                    } else {
                        jobDetails.is_bookmarked = true
                        jobsViewModel.bookmarkJob(jobDetails)
                        Snackbar.make(view, "Job bookmarked", Snackbar.LENGTH_SHORT)
                            .setAction("Undo") {
                                jobsViewModel.deleteJob(jobDetails.id)
                                jobDetails.is_bookmarked = false
                                //notify adapters notifydatasetchanged

                            }.show()
                        bookmarkButton.setImageResource(R.drawable.ic_bookmark_fill)
                    }

                }
            }
        } ?: run {
            binding.title.text = "Job details unavailable"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

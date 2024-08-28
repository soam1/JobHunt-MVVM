package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.akashsoam.jobhunt.databinding.FragmentJobDetailBinding
import com.akashsoam.jobhunt.ui.viewmodel.JobsViewModel

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

        job?.let {
            binding.apply {
                title.text = it.title
                location.text = it.primary_details.Place
                salary.text = it.primary_details.Salary
                phone.text = it.whatsapp_no
                bookmarkButton.setOnClickListener {
                    jobsViewModel.bookmarkJob(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class JobDetailsFragment : Fragment() {
    private lateinit var binding: FragmentJobDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val job = JobDetailsFragmentArgs.fromBundle(requireArguments()).job

        binding.textViewTitle.text = job.title
        binding.textViewLocation.text = job.location
        binding.textViewSalary.text = job.salary
        binding.textViewPhone.text = job.phone
    }
}

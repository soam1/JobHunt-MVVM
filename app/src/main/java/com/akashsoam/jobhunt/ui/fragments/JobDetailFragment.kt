package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akashsoam.jobhunt.databinding.FragmentJobDetailBinding
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.ui.JobViewModel

class JobDetailFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailBinding
    private lateinit var viewModel: JobViewModel
    private var jobEntity: JobEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assuming the jobEntity is passed as an argument
        jobEntity = arguments?.getParcelable<JobEntity>("jobEntity")

        jobEntity?.let {
            binding.title.text = it.title
            binding.location.text = it.location
            binding.salary.text = it.salary
            binding.phone.text = it.phone
//            binding.detailsTextView.text = it.details
        }
    }
}

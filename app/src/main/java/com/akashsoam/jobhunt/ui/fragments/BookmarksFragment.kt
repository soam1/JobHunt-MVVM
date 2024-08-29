package com.akashsoam.jobhunt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.adapters.JobAdapter
import com.akashsoam.jobhunt.databinding.FragmentBookmarksBinding
import com.akashsoam.jobhunt.models.Job
import com.akashsoam.jobhunt.models.PrimaryDetails
import com.akashsoam.jobhunt.ui.viewmodel.JobsViewModel
import com.google.android.material.snackbar.Snackbar

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
//        bookmarksAdapter = JobAdapter { jobEntity ->
//            // Handle click on bookmarked job
//            jobsViewModel.deleteJob(jobEntity.id)
//            Toast.makeText(context, "Job removed from bookmarks", Toast.LENGTH_SHORT).show()
//            bookmarksAdapter.notifyDataSetChanged()
//            //change the src of the ImageButton to unbookmarked
//        }

        bookmarksAdapter = JobAdapter { job ->
            // Handle click on bookmarked job
//            //go to the job details page
            val action = BookmarksFragmentDirections.actionBookmarksFragmentToJobDetailFragment(job)
            binding.root.findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarksAdapter
        }

        jobsViewModel.getBookmarkedJobs().observe(viewLifecycleOwner) { jobs ->
            bookmarksAdapter.submitList(jobs.map {
                Job(
                    id = it.id,
                    title = it.title,
                    company_name = it.company_name,
                    primary_details = PrimaryDetails(
                        Place = it.place,
                        Salary = it.salary,
                        Job_Type = "",
                        Experience = "",
                        Fees_Charged = "",
                        Qualification = ""
                    ),
                    whatsapp_no = it.whatsapp_no,
                    is_bookmarked = true,
                    updated_on = it.updated_on
                )
            })
        }


        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val job = bookmarksAdapter.jobs[position]
                jobsViewModel.deleteJob(job.id)
                Snackbar.make(binding.root, "Job removed from bookmarks", Snackbar.LENGTH_SHORT)
                    .setAction("Undo") {
                        jobsViewModel.bookmarkJob(job)
                    }.show()

            }
        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

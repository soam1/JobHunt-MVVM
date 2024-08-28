package com.akashsoam.jobhunt.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.databinding.ItemJobBinding
import com.akashsoam.jobhunt.models.Job

class JobsAdapter(private val onJobClick: (Job) -> Unit) : RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    private val jobList = mutableListOf<Job>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        holder.bind(job)
        holder.itemView.setOnClickListener {
            onJobClick(job)
        }
    }

    override fun getItemCount(): Int = jobList.size

    fun submitList(jobs: List<Job>) {
        jobList.clear()
        jobList.addAll(jobs)
        notifyDataSetChanged()
    }

    class JobViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Job) {
            binding.jobTitle.text = job.title
            binding.jobLocation.text = job.primary_details.Place
            binding.jobSalary.text = job.primary_details.Salary
            binding.companyName.text = job.company_name
        }
    }
}

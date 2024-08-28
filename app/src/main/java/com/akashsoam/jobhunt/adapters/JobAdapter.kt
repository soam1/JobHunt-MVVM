package com.akashsoam.jobhunt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.databinding.ItemJobBinding
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job

package com.akashsoam.jobhunt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.database.entities.JobEntity
import com.akashsoam.jobhunt.databinding.ItemJobBinding

class JobAdapter(private val onClick: (JobEntity) -> Unit) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private var jobs = listOf<JobEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    fun submitList(newJobs: List<JobEntity>) {
        jobs = newJobs
        notifyDataSetChanged()
    }

    inner class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobEntity) {
            binding.apply {
                jobTitle.text = job.title
                jobLocation.text = job.location
                jobSalary.text = job.salary
                jobPhone.text = job.phone
                root.setOnClickListener { onClick(job) }
            }
        }
    }
}


class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem == newItem
    }
}

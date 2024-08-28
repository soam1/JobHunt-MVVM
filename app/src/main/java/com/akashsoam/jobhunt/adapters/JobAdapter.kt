package com.akashsoam.jobhunt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.databinding.ItemJobBinding
import com.akashsoam.jobhunt.db.JobEntity
import com.akashsoam.jobhunt.models.Job

class JobAdapter(
    private val onItemClickListener: (JobEntity) -> Unit
) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private var jobList: List<JobEntity> = emptyList()

    fun submitList(jobs: List<JobEntity>) {
        jobList = jobs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobList[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    inner class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobEntity) {
            binding.apply {
                title.text = job.title
                location.text = job.location
                salary.text = job.salary
                root.setOnClickListener {
                    onItemClickListener(job)
                }
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

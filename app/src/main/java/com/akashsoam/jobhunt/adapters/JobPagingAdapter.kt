package com.akashsoam.jobhunt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.databinding.ItemJobBinding
import com.akashsoam.jobhunt.db.JobEntity

class JobPagingAdapter(private val onClick: (JobEntity) -> Unit) :
    PagingDataAdapter<JobEntity, JobPagingAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = getItem(position)
        job?.let {
            holder.bind(it)
        }
    }

    inner class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: JobEntity) {
            binding.apply {
                title.text = job.title
                location.text = job.location
                salary.text = job.salary
                phone.text = job.phone
                root.setOnClickListener { onClick(job) }
            }
        }
    }

    private class JobDiffCallback : DiffUtil.ItemCallback<JobEntity>() {
        override fun areItemsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean {
            return oldItem == newItem
        }
    }
}

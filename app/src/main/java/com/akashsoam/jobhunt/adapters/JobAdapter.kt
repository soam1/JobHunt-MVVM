package com.akashsoam.jobhunt.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.jobhunt.R
import com.akashsoam.jobhunt.databinding.ItemJobBinding
import com.akashsoam.jobhunt.models.Job

class JobAdapter(
    private val onClick: (Job) -> Unit,
    private val bookmarkClickListener: (Job) -> Unit
) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    var jobs = listOf<Job>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]
        holder.bind(job)
        holder.binding.bookmarkButton.setImageResource(
            if (job.is_bookmarked) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark_border
        )

        holder.binding.bookmarkButton.setOnClickListener {
            job.is_bookmarked = !job.is_bookmarked
            bookmarkClickListener(job)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    fun submitList(newJobs: List<Job>) {
        jobs = newJobs
        notifyDataSetChanged()
    }

    inner class JobViewHolder(val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(job: Job) {
            binding.apply {
                title.text = job.title
                if (job.primary_details != null) {
                    location.text = job.primary_details.Place
                } else {
                    location.text = "Location not available"
                }
                if (job.primary_details != null) {
                    salary.text = job.primary_details.Salary
                } else {
                    salary.text = "Salary not disclosed"
                }
                phone.text = job.whatsapp_no
                bookmarkButton.setImageResource(
                    if (job.is_bookmarked) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark_border
                )
                root.setOnClickListener { onClick(job) }
            }
        }
    }
}


//class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
//    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
//        return oldItem == newItem
//    }
//}

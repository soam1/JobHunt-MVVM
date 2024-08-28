package com.akashsoam.jobhunt.util

import com.akashsoam.jobhunt.db.Job
import com.akashsoam.jobhunt.models.Job

fun Job.toJobEntity(): com.akashsoam.jobhunt.db.Job {
    return com.akashsoam.jobhunt.db.Job(
        id = this.id.toString(),
        title = this.title,
        location = this.locations.joinToString(", ") { it.locationName },
        salary = "${(this.salary_max + this.salary_min) / 2}",
        phone = this.whatsapp_no,
        details = this.content
    )
}

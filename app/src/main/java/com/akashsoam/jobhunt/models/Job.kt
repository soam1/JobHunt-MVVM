package com.akashsoam.jobhunt.models

data class Job(
    val id: String,
    val title: String,
    val location: String,
    val salary: String?,
    val phone: String?
)

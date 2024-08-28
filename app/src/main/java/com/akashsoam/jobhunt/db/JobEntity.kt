package com.akashsoam.jobhunt.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class JobEntity(
    @PrimaryKey val id: String,
    val title: String,
    val location: String,
    val salary: String,
    val phone: String,
    val details: String
)
package com.akashsoam.jobhunt.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class JobEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val company_name: String,
    val place: String,          // From PrimaryDetails
    val salary: String,         // From PrimaryDetails
    val whatsapp_no: String,    // Phone number
    val updated_on: String
)

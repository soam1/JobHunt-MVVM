package com.akashsoam.jobhunt.models

import java.io.Serializable

data class Job(
    val id: Int,
    val title: String,
    val company_name: String,
    val primary_details: PrimaryDetails, // Includes location, salary, etc.
    val whatsapp_no: String,  // Represents the phone number
    val is_bookmarked: Boolean,
    val updated_on: String
) : Serializable


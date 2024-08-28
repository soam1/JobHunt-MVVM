package com.akashsoam.jobhunt.util

import android.content.Context
import com.akashsoam.jobhunt.db.JobDao
import com.akashsoam.jobhunt.db.JobDatabase

object JobDatabaseProvider {
    fun provideJobDao(context: Context): JobDao {
        return JobDatabase.getDatabase(context).jobDao()
    }
}

package com.akashsoam.jobhunt.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.akashsoam.jobhunt.model.Job

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Delete
    suspend fun deleteJob(job: Job)

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): LiveData<List<Job>>
}

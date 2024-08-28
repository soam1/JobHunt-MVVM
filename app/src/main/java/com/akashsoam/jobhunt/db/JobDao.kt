package com.akashsoam.jobhunt.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(job: JobEntity)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllJobs(): LiveData<List<JobEntity>>

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun deleteJob(jobId: Int)
}

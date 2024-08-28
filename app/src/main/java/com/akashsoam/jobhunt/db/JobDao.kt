package com.akashsoam.jobhunt.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkJob(job: JobEntity)

    @Delete
    suspend fun removeJob(job: JobEntity)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllBookmarkedJobs(): LiveData<List<JobEntity>>
}

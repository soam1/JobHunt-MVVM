package com.akashsoam.jobhunt.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_table")
data class JobEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val location: String,
    val salary: String,
    val phone: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(location)
        parcel.writeString(salary)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JobEntity> {
        override fun createFromParcel(parcel: Parcel): JobEntity {
            return JobEntity(parcel)
        }

        override fun newArray(size: Int): Array<JobEntity?> {
            return arrayOfNulls(size)
        }
    }
}

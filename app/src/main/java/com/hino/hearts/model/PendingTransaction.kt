package com.hino.hearts.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Dihardja Software on 2020-02-18.
 */
data class PendingTransaction(
    val id: Int,
    val title: String?,
    val date: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PendingTransaction> {
        override fun createFromParcel(parcel: Parcel): PendingTransaction {
            return PendingTransaction(parcel)
        }

        override fun newArray(size: Int): Array<PendingTransaction?> {
            return arrayOfNulls(size)
        }
    }
}
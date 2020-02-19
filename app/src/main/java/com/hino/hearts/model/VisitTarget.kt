package com.hino.hearts.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Dihardja Software on 2020-02-17.
 */
data class VisitTarget(
    val name: String?,
    val completed: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (completed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VisitTarget> {
        override fun createFromParcel(parcel: Parcel): VisitTarget {
            return VisitTarget(parcel)
        }

        override fun newArray(size: Int): Array<VisitTarget?> {
            return arrayOfNulls(size)
        }
    }
}
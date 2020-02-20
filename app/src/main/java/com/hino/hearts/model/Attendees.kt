package com.hino.hearts.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Dihardja Software on 2020-02-20.
 */
data class Attendees(
    val id: Int,
    val account: String?,
    val name: String?,
    val isInvited: Boolean,
    val isCheckedIn: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(account)
        parcel.writeString(name)
        parcel.writeByte(if (isInvited) 1 else 0)
        parcel.writeByte(if (isCheckedIn) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attendees> {
        override fun createFromParcel(parcel: Parcel): Attendees {
            return Attendees(parcel)
        }

        override fun newArray(size: Int): Array<Attendees?> {
            return arrayOfNulls(size)
        }
    }

}
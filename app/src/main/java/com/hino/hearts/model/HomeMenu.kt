package com.hino.hearts.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Dihardja Software on 2020-02-13.
 */
data class HomeMenu(
    val imgRes: Int,
    val name: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imgRes)
        parcel.writeInt(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeMenu> {
        override fun createFromParcel(parcel: Parcel): HomeMenu {
            return HomeMenu(parcel)
        }

        override fun newArray(size: Int): Array<HomeMenu?> {
            return arrayOfNulls(size)
        }
    }
}
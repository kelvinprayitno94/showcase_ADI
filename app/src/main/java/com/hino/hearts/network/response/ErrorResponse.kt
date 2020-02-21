package com.hino.hearts.network.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Dihardja Software on 2020-02-14.
 */

open class ErrorResponse() : Parcelable{
    @SerializedName("meta")
    var meta: Meta = Meta()

    constructor(parcel: Parcel) : this() {
        parcel.readValue(meta::class.java.classLoader)
        meta.message = parcel.readString()
        meta.success = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(meta)
        parcel.writeString(meta.message)
        parcel.writeByte(if (meta.success) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorResponse> {
        override fun createFromParcel(parcel: Parcel): ErrorResponse {
            return ErrorResponse(parcel)
        }

        override fun newArray(size: Int): Array<ErrorResponse?> {
            return arrayOfNulls(size)
        }
    }

    inner class Meta {
        @SerializedName("message")
        var message: String? = ""
        @SerializedName("success")
        var success: Boolean = false
    }
}
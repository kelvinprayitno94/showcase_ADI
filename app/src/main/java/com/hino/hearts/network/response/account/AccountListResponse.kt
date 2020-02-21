package com.hino.hearts.network.response.account

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hino.hearts.network.response.ErrorResponse

class AccountListResponse() : ErrorResponse(), Parcelable{
    @SerializedName("data")
    var listData : List<AccListData> = ArrayList()



    constructor(parcel: Parcel) : this() {
        parcel.readList(listData, AccListData::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(listData)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccountListResponse> {
        override fun createFromParcel(parcel: Parcel): AccountListResponse {
            return AccountListResponse(parcel)
        }

        override fun newArray(size: Int): Array<AccountListResponse?> {
            return arrayOfNulls(size)
        }
    }

    class AccListData() : Parcelable{

        @SerializedName("id")
        var id : Int = 0
        @SerializedName("accountName")
        var accountName : String? = ""
        @SerializedName("address")
        var address : String? = ""

        constructor(parcel: Parcel) : this() {
            id = parcel.readInt()
            accountName = parcel.readString()
            address = parcel.readString()
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.run {
                writeInt(id)
                writeString(accountName)
                writeString(address)
            }
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<AccListData> {
            override fun createFromParcel(parcel: Parcel): AccListData {
                return AccListData(parcel)
            }

            override fun newArray(size: Int): Array<AccListData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
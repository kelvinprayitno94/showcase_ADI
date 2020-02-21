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
        @SerializedName("account")
        var account : AccountData? = AccountData()
        @SerializedName("contact")
        var contact : List<ContactData>? = ArrayList()
        @SerializedName("opportunity")
        var opportunity : List<OpportunityData>? = ArrayList()
        @SerializedName("vehicle")
        var vehicle : List<VehicleData>? = ArrayList()

        constructor(parcel: Parcel) : this() {
            account = parcel.readParcelable(AccountData::class.java.classLoader)
            contact = parcel.createTypedArrayList(ContactData)
            opportunity = parcel.createTypedArrayList(OpportunityData)
            vehicle = parcel.createTypedArrayList(VehicleData)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeParcelable(account, flags)
            parcel.writeTypedList(contact)
            parcel.writeTypedList(opportunity)
            parcel.writeTypedList(vehicle)
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

    class AccountData(): Parcelable{
        @SerializedName("id")
        var id : Int = 0
        @SerializedName("accountName")
        var accountName : String? = ""
        @SerializedName("address")
        var address : String? = ""
        @SerializedName("city")
        var city : String? = ""

        constructor(parcel: Parcel) : this() {
            id = parcel.readInt()
            accountName = parcel.readString()
            address = parcel.readString()
            city = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(accountName)
            parcel.writeString(address)
            parcel.writeString(city)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<AccountData> {
            override fun createFromParcel(parcel: Parcel): AccountData {
                return AccountData(parcel)
            }

            override fun newArray(size: Int): Array<AccountData?> {
                return arrayOfNulls(size)
            }
        }
    }

    class ContactData(): Parcelable{
        @SerializedName("name")
        var name : String? = ""
        @SerializedName("phoneNumber")
        var phoneNumber : String? = ""

        constructor(parcel: Parcel) : this() {
            name = parcel.readString()
            phoneNumber = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(phoneNumber)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ContactData> {
            override fun createFromParcel(parcel: Parcel): ContactData {
                return ContactData(parcel)
            }

            override fun newArray(size: Int): Array<ContactData?> {
                return arrayOfNulls(size)
            }
        }
    }

    class OpportunityData():Parcelable{
        @SerializedName("opportunityName")
        var opportunityName : String? = ""
        @SerializedName("name")
        var name : String? = ""
        @SerializedName("opportunityValue")
        var opportunityValue : String? = ""

        constructor(parcel: Parcel) : this() {
            opportunityName = parcel.readString()
            name = parcel.readString()
            opportunityValue = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(opportunityName)
            parcel.writeString(name)
            parcel.writeString(opportunityValue)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<OpportunityData> {
            override fun createFromParcel(parcel: Parcel): OpportunityData {
                return OpportunityData(parcel)
            }

            override fun newArray(size: Int): Array<OpportunityData?> {
                return arrayOfNulls(size)
            }
        }
    }

    class VehicleData(): Parcelable{
        @SerializedName("name")
        var name : String? = ""
        @SerializedName("code")
        var code : String? = ""

        constructor(parcel: Parcel) : this() {
            name = parcel.readString()
            code = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(code)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<VehicleData> {
            override fun createFromParcel(parcel: Parcel): VehicleData {
                return VehicleData(parcel)
            }

            override fun newArray(size: Int): Array<VehicleData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
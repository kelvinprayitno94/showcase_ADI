package com.hino.hearts.network.response.approve

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hino.hearts.network.response.ErrorResponse

class ApprovalListResponse : ErrorResponse() {
    @SerializedName("data")
    var listData: List<ApprovalListData> = ArrayList()

    var selected: Int = 0

    class ApprovalListData() : Parcelable{

        @SerializedName("id")
        var id: Int = 0
        @SerializedName("approval")
        var approval: ApprovalData? = ApprovalData()
        @SerializedName("orders")
        var orders: List<OrdersData>? = ArrayList()
        @SerializedName("approvalProgress")
        var approvalProgress: List<ApprovalProgressData>? = ArrayList()
        @SerializedName("type")
        var type: String? = ""
        @SerializedName("discount")
        var discount: Int? = 0
        @SerializedName("totalAmount")
        var totalAmount: Int? = 0

        constructor(parcel: Parcel) : this() {
            id = parcel.readInt()
            approval = parcel.readParcelable(ApprovalData::class.java.classLoader)
            orders = parcel.createTypedArrayList(OrdersData)
            approvalProgress = parcel.createTypedArrayList(ApprovalProgressData)
            type = parcel.readString()
            discount = parcel.readValue(Int::class.java.classLoader) as? Int
            totalAmount = parcel.readValue(Int::class.java.classLoader) as? Int
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeParcelable(approval, flags)
            parcel.writeTypedList(orders)
            parcel.writeTypedList(approvalProgress)
            parcel.writeString(type)
            parcel.writeValue(discount)
            parcel.writeValue(totalAmount)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ApprovalListData> {
            override fun createFromParcel(parcel: Parcel): ApprovalListData {
                return ApprovalListData(parcel)
            }

            override fun newArray(size: Int): Array<ApprovalListData?> {
                return arrayOfNulls(size)
            }
        }

    }

    class ApprovalData() : Parcelable{
        @SerializedName("soNum")
        var soNum: String? = "-"
        @SerializedName("orderType")
        var orderType: String? = "-"
        @SerializedName("customerName")
        var customerName: String? = ""
        @SerializedName("customerCode")
        var customerCode: String? = ""
        @SerializedName("prDocNo")
        var prDocNo: String? = "-"
        @SerializedName("soDate")
        var soDate: String? = "-"

        constructor(parcel: Parcel) : this() {
            soNum = parcel.readString()
            orderType = parcel.readString()
            customerName = parcel.readString()
            customerCode = parcel.readString()
            prDocNo = parcel.readString()
            soDate = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(soNum)
            parcel.writeString(orderType)
            parcel.writeString(customerName)
            parcel.writeString(customerCode)
            parcel.writeString(prDocNo)
            parcel.writeString(soDate)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ApprovalData> {
            override fun createFromParcel(parcel: Parcel): ApprovalData {
                return ApprovalData(parcel)
            }

            override fun newArray(size: Int): Array<ApprovalData?> {
                return arrayOfNulls(size)
            }
        }
    }

    class ApprovalProgressData() : Parcelable {
        @SerializedName("name")
        var name: String? = ""
        @SerializedName("approved")
        var approved: Boolean? = false
        @SerializedName("date")
        var date: String? = ""
        @SerializedName("approver")
        var approver: Approver? = Approver()

        constructor(parcel: Parcel) : this() {
            name = parcel.readString()
            approved = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
            date = parcel.readString()
            approver = parcel.readParcelable(Approver::class.java.classLoader)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeValue(approved)
            parcel.writeString(date)
            parcel.writeParcelable(approver, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ApprovalProgressData> {
            override fun createFromParcel(parcel: Parcel): ApprovalProgressData {
                return ApprovalProgressData(parcel)
            }

            override fun newArray(size: Int): Array<ApprovalProgressData?> {
                return arrayOfNulls(size)
            }
        }
    }

    class Approver() : Parcelable{
        @SerializedName("id")
        var id: Int? = 0
        @SerializedName("email")
        var email: String? = ""
        @SerializedName("phoneNumber")
        var phoneNumber: String? = ""
        @SerializedName("name")
        var name: String? = ""
        @SerializedName("roleId")
        var roleId: Int? = 0
        @SerializedName("employeeId")
        var employeeId: String? = ""

        constructor(parcel: Parcel) : this() {
            id = parcel.readValue(Int::class.java.classLoader) as? Int
            email = parcel.readString()
            phoneNumber = parcel.readString()
            name = parcel.readString()
            roleId = parcel.readValue(Int::class.java.classLoader) as? Int
            employeeId = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(id)
            parcel.writeString(email)
            parcel.writeString(phoneNumber)
            parcel.writeString(name)
            parcel.writeValue(roleId)
            parcel.writeString(employeeId)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Approver> {
            override fun createFromParcel(parcel: Parcel): Approver {
                return Approver(parcel)
            }

            override fun newArray(size: Int): Array<Approver?> {
                return arrayOfNulls(size)
            }
        }
    }

    class OrdersData() : Parcelable{
        @SerializedName("orderCode")
        var orderCode: String? = ""
        @SerializedName("name")
        var name: String? = ""
        @SerializedName("warehouse")
        var warehouse: String? = ""
        @SerializedName("value")
        var value: String? = ""
        @SerializedName("id")
        var id: Int? = 0
        @SerializedName("qty")
        var qty: Int? = 0

        constructor(parcel: Parcel) : this() {
            orderCode = parcel.readString()
            name = parcel.readString()
            warehouse = parcel.readString()
            value = parcel.readString()
            id = parcel.readValue(Int::class.java.classLoader) as? Int
            qty = parcel.readValue(Int::class.java.classLoader) as? Int
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(orderCode)
            parcel.writeString(name)
            parcel.writeString(warehouse)
            parcel.writeString(value)
            parcel.writeValue(id)
            parcel.writeValue(qty)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<OrdersData> {
            override fun createFromParcel(parcel: Parcel): OrdersData {
                return OrdersData(parcel)
            }

            override fun newArray(size: Int): Array<OrdersData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
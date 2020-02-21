package com.hino.hearts.network.response.account

import com.google.gson.annotations.SerializedName
import com.hino.hearts.network.response.ErrorResponse

class AccountDetailResponse : ErrorResponse(){
    @SerializedName("data")
    var listData : List<AccountDetailData> = ArrayList()

    inner class AccountDetailData {

        @SerializedName("id")
        var id : Int = 0
        @SerializedName("accountName")
        var accountName : String? = ""
        @SerializedName("address")
        var address : String? = ""

    }
}
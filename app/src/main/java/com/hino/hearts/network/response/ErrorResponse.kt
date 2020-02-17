package com.hino.hearts.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Dihardja Software on 2020-02-14.
 */
class ErrorResponse {
    @SerializedName("meta")
    val meta: Meta = Meta()

    inner class Meta {
        @SerializedName("message")
        val message: String = ""
        @SerializedName("success")
        val success: Boolean = false
    }
}
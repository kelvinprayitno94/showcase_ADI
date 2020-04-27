package com.showcase.movie.network.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoResponse{
    @SerializedName("id")
    @Expose
    var id : Int = 0
    @SerializedName("results")
    @Expose
    var results : List<ResultModel> = ArrayList()

    class ResultModel{
        @SerializedName("id")
        @Expose
        var id : String? = ""
        @SerializedName("key")
        @Expose
        var key : String? = ""
        @SerializedName("name")
        @Expose
        var name : String? = ""
        @SerializedName("site")
        @Expose
        var site : String? = ""


    }
}
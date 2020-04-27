package com.showcase.movie.network.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesResponse{
    @SerializedName("results")
    @Expose
    var Movie : List<MoviesModel> = ArrayList()

    @SerializedName("page")
    @Expose
    var page : Int? = 1

    @SerializedName("total_pages")
    @Expose
    var total_pages : Int? = 1

    class MoviesModel() : Parcelable{
        @SerializedName("poster_path")
        @Expose
        var poster_path : String? = ""
        @SerializedName("overview")
        @Expose
        var overview : String? = ""
        @SerializedName("backdrop_path")
        @Expose
        var backdrop_path : String? = ""
        @SerializedName("title")
        @Expose
        var title : String? = ""
        @SerializedName("release_date")
        @Expose
        var release_date : String? = ""
        @SerializedName("vote_average")
        @Expose
        var vote_average : Double? = 0.0
        @SerializedName("id")
        @Expose
        var id : Int? = 0

        constructor(parcel: Parcel) : this() {
            poster_path = parcel.readString()
            overview = parcel.readString()
            backdrop_path = parcel.readString()
            title = parcel.readString()
            release_date = parcel.readString()
            vote_average = parcel.readValue(Double::class.java.classLoader) as? Double
            id = parcel.readValue(Int::class.java.classLoader) as? Int
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(poster_path)
            parcel.writeString(overview)
            parcel.writeString(backdrop_path)
            parcel.writeString(title)
            parcel.writeString(release_date)
            parcel.writeValue(vote_average)
            parcel.writeValue(id)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<MoviesModel> {
            override fun createFromParcel(parcel: Parcel): MoviesModel {
                return MoviesModel(parcel)
            }

            override fun newArray(size: Int): Array<MoviesModel?> {
                return arrayOfNulls(size)
            }
        }

    }
}
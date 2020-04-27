package com.showcase.movie.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenresResponse{
    @SerializedName("genres")
    @Expose
    var genresList : List<GenresModel> = ArrayList()

    class GenresModel {
        @SerializedName("name")
        @Expose
        var name : String = ""
        @SerializedName("id")
        @Expose
        var id : Int = 0
    }
}
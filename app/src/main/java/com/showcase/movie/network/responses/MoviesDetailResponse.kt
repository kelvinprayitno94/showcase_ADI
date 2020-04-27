package com.showcase.movie.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviesDetailResponse {
    @SerializedName("homepage")
    @Expose
    var homepage: String? = ""

    @SerializedName("release_date")
    @Expose
    var release_date: String? = ""

    @SerializedName("popularity")
    @Expose
    var popularity : Double? = 0.0

    @SerializedName("runtime")
    @Expose
    var runtime: Int? = 0

    @SerializedName("vote_average")
    @Expose
    var vote_average: Double? = 0.0

    @SerializedName("vote_count")
    @Expose
    var vote_count: Int? = 0

    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("genres")
    @Expose
    var genres: List<GenreModel>? = ArrayList()

    class GenreModel(){
        @SerializedName("name")
        @Expose
        var name: String? = ""

        @SerializedName("id")
        @Expose
        var id: Int? = 0
    }
}
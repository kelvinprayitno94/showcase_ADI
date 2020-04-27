package com.showcase.movie.network

import com.showcase.movie.network.responses.*
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Dihardja Software on 2020-02-10.
 */
interface RetrofitService {
    @GET("3/genre/movie/list")
    fun fetchGenre(
        @Query("api_key") apiKey: String?
    ): Deferred<GenresResponse>

    @GET("3/discover/movie")
    fun fetchMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?,
        @Query("with_genres") with_genres: String?
    ): Deferred<MoviesResponse>

    @GET("3/movie/{id_movie}")
    fun fetchMovieDetail(
        @Path("id_movie") id: Int?,
        @Query("api_key") apiKey: String?
    ): Deferred<MoviesDetailResponse>

    @GET("3/movie/{movie_id}/reviews")
    fun fetchReview(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): Deferred<ReviewResponse>

    @GET("3/movie/{movie_id}/videos")
    fun fetchYoutubeLink(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String?
    ): Deferred<VideoResponse>
}
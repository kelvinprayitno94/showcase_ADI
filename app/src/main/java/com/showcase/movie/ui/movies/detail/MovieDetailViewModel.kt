package com.showcase.movie.ui.movies.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.BuildConfig
import com.showcase.movie.network.HinoService
import com.showcase.movie.network.RetrofitService
import com.showcase.movie.network.responses.MoviesDetailResponse
import com.showcase.movie.network.responses.MoviesResponse
import com.showcase.movie.network.responses.ReviewResponse
import com.showcase.movie.network.responses.VideoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Dihardja Software on 2020-02-11.
 */
class MovieDetailViewModel : ViewModel() {
    var movieDetailLiveData: MutableLiveData<MoviesResponse.MoviesModel> = MutableLiveData()
    var detailLiveData: MutableLiveData<MoviesDetailResponse> = MutableLiveData()
    var reviewLiveData: MutableLiveData<ReviewResponse> = MutableLiveData()
    var videoLiveData: MutableLiveData<VideoResponse> = MutableLiveData()
    var genreLiveData: MutableLiveData<String> = MutableLiveData()
    var youtubeLinkLiveData: MutableLiveData<String> = MutableLiveData()
    var showYoutubeLiveData: MutableLiveData<String> = MutableLiveData()

    var movieList : MutableList<MoviesResponse.MoviesModel> = ArrayList()

    var page = 0
    var total_page = 1
    var _id = 0
    var isLoadReview = false

    init {

    }

    fun init(data: MoviesResponse.MoviesModel?, id: Int) {
        movieDetailLiveData.value = data

        _id = id
    }

    fun getMovies() {
        CoroutineScope(Dispatchers.Main).launch {

            try {

                val call =
                    HinoService.create(RetrofitService::class.java)
                        .fetchMovieDetail(_id, BuildConfig.API_KEY)

                val response = call.await()

                detailLiveData.value = response

                var genre = ""
                for (index in response.genres!!.indices) {
                    genre += "${response.genres!![index].name} "

                    if (index!=response.genres!!.size-1)
                        genre += ","
                }

                genreLiveData.value = genre

            } catch (t: Throwable) {
                t.printStackTrace()
                when (t) {
                    is IOException -> {

                    }
                    is HttpException -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun getUserReview() {
        if (!isLoadReview && page <= total_page) {
            CoroutineScope(Dispatchers.Main).launch {

                try {

                    isLoadReview = true
                    page += 1

                    val call =
                        HinoService.create(RetrofitService::class.java)
                            .fetchReview(_id, BuildConfig.API_KEY, page)

                    val response = call.await()


                    reviewLiveData.value = response

                    if (response.results!!.isNotEmpty()){
                        page = response.page!!
                        total_page = response.total_pages!!
                    }


                } catch (t: Throwable) {
                    isLoadReview = false
                    t.printStackTrace()
                    when (t) {
                        is IOException -> {

                        }
                        is HttpException -> {

                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    fun getYoutubeLink(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                val call =
                    HinoService.create(RetrofitService::class.java)
                        .fetchYoutubeLink(id, BuildConfig.API_KEY)

                val response = call.await()

                videoLiveData.value = response

                for (_data in response.results.iterator()){
                    if (_data.site!!.contains("YouTube")){
                        youtubeLinkLiveData.value = BuildConfig.YOUTUBE_URL+_data.key
                    }
                }

            } catch (t: Throwable) {
                t.printStackTrace()
                when (t) {
                    is IOException -> {

                    }
                    is HttpException -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun showYoutube(){
        showYoutubeLiveData.value = youtubeLinkLiveData.value
    }

    fun finishLoading() {
        isLoadReview = false
    }
}
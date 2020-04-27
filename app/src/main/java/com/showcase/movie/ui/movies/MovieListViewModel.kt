package com.showcase.movie.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.BuildConfig
import com.showcase.movie.network.HinoService
import com.showcase.movie.network.RetrofitService
import com.showcase.movie.network.responses.GenresResponse
import com.showcase.movie.network.responses.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Dihardja Software on 2020-02-11.
 */
class MovieListViewModel : ViewModel() {
    var genreListLiveData: MutableLiveData<List<MoviesResponse.MoviesModel>> = MutableLiveData()

    var movieList : MutableList<MoviesResponse.MoviesModel> = ArrayList()

    var page = 0
    var total_page = 1
    var genre_id = 0

    var isLoading = false

    init {

    }

    fun init(id: Int){
        genre_id = id
    }

    fun getMovies(){
        if (!isLoading && page <= total_page) {
            isLoading = true
            page += 1
            CoroutineScope(Dispatchers.Main).launch {

                try {

                    val call =
                        HinoService.create(RetrofitService::class.java)
                            .fetchMovies(BuildConfig.API_KEY, page, genre_id.toString())

                    val response = call.await()

                    if (response.Movie.isNotEmpty()) {

                        total_page = response.total_pages!!
                        page = response.page!!

                        movieList.addAll(response.Movie)

                        genreListLiveData.value = movieList
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
    }

    fun finishLoading(){
        isLoading = false
    }


}
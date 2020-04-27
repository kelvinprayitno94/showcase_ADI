package com.showcase.movie.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hino.hearts.BuildConfig
import com.showcase.movie.network.HinoService
import com.showcase.movie.network.RetrofitService
import com.showcase.movie.network.responses.GenresResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Dihardja Software on 2020-02-11.
 */
class GenreListViewModel : ViewModel() {
    var genreListLiveData: MutableLiveData<List<GenresResponse.GenresModel>> = MutableLiveData()

    init {

    }

    fun getGenres(){
        CoroutineScope(Dispatchers.Main).launch  {

            try {

                val call =
                    HinoService.create(RetrofitService::class.java).fetchGenre(BuildConfig.API_KEY)

                val response = call.await()

                if (response.genresList.isNotEmpty()) {
                    genreListLiveData.value = response.genresList
                }

            } catch (t: Throwable){
                t.printStackTrace()
                when(t){
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
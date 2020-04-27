package com.showcase.movie.util.module

import com.showcase.movie.ui.genre.GenreListViewModel
import com.showcase.movie.ui.movies.MovieListViewModel
import com.showcase.movie.ui.movies.detail.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Dihardja Software on 2020-02-10.
 */
val moduleViewModel = module {
    viewModel { MovieListViewModel() }
    viewModel { GenreListViewModel() }
    viewModel { MovieDetailViewModel() }
}
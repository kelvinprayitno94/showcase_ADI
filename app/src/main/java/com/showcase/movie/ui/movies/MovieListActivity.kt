package com.showcase.movie.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityMovieListBinding
import com.showcase.movie.adapter.MoviesAdapter
import com.showcase.movie.network.responses.MoviesResponse
import com.showcase.movie.ui.BaseActivity
import com.showcase.movie.ui.movies.detail.MovieDetailActivity
import com.showcase.movie.util.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.layout_toolbar_home.tb_home
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListActivity : BaseActivity<ActivityMovieListBinding>() {

    private val viewModel by viewModel<MovieListViewModel>()

    lateinit var adapter : MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_movie_list)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun initObserver() {
        viewModel.genreListLiveData.observe(this, Observer {

            if (it.isNotEmpty()) {
                adapter.updateAdapter(it)
            }else{
                Toast.makeText(this@MovieListActivity, "Tidak ada data", Toast.LENGTH_SHORT).show()
                finish()
            }

            viewModel.finishLoading()
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel

        viewModel.init(intent.getIntExtra("id", 0))

        viewModel.getMovies()
    }

    override fun initEvent() {
        adapter = MoviesAdapter(this@MovieListActivity,
            object : MoviesAdapter.OnAdapterTap {
                override fun onTap(data: MoviesResponse.MoviesModel) {
                    startActivity(
                        Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                            .putExtra("data", data)
                    )
                }

                override fun isLastPos() {
                    viewModel.getMovies()
                }

            })

        rv_movie_list.adapter = adapter
        rv_movie_list.layoutManager =
            LinearLayoutManager(this@MovieListActivity, RecyclerView.VERTICAL, false)
        rv_movie_list.addItemDecoration(
            DividerItemDecoration(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.transparent_divider
                )
            )
        )
    }

    private fun initLayout() {
        setSupportActionBar(tb_home)
    }
}

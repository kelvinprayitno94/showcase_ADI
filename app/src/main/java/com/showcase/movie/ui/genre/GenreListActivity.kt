package com.showcase.movie.ui.genre

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityGenreListBinding
import com.showcase.movie.adapter.GenresAdapter
import com.showcase.movie.network.responses.GenresResponse
import com.showcase.movie.ui.BaseActivity
import com.showcase.movie.ui.movies.MovieListActivity
import kotlinx.android.synthetic.main.activity_genre_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenreListActivity : BaseActivity<ActivityGenreListBinding>() {

    private val viewModel by viewModel<GenreListViewModel>()

    lateinit var adapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_genre_list)

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun initObserver() {
        viewModel.genreListLiveData.observe(this, Observer {
            adapter = GenresAdapter(this@GenreListActivity,
                it,
                object : GenresAdapter.OnAdapterTap {
                    override fun onTap(data: GenresResponse.GenresModel) {
                        startActivity(
                            Intent(
                                this@GenreListActivity,
                                MovieListActivity::class.java
                            ).putExtra("id", data.id)
                        )
                    }

                })

            rv_genre_list.adapter = adapter
            rv_genre_list.layoutManager =
                GridLayoutManager(this@GenreListActivity, 2, RecyclerView.VERTICAL, false)
        })
    }

    override fun initViewModel() {
        binding.viewModel = viewModel

        viewModel.getGenres()
    }

    override fun initEvent() {

    }

    private fun initLayout() {

    }
}

package com.showcase.movie.ui.movies.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hino.hearts.BuildConfig
import com.hino.hearts.R
import com.hino.hearts.databinding.ActivityMovieDetailBinding
import com.showcase.movie.adapter.ReviewAdapter
import com.showcase.movie.network.responses.MoviesResponse
import com.showcase.movie.ui.BaseActivity
import com.showcase.movie.util.DividerItemDecoration
import com.showcase.movie.util.GlideManager
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {

    private val viewModel by viewModel<MovieDetailViewModel>()

    lateinit var adapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding(R.layout.activity_movie_detail)

        binding.viewModel = viewModel

        initObserver()
        initViewModel()
        initEvent()
        initLayout()
    }

    override fun initObserver() {

        viewModel.detailLiveData.observe(this, Observer {
            it.id?.let {id ->
                viewModel.getUserReview()
                viewModel.getYoutubeLink(id)
            }
        })

        viewModel.reviewLiveData.observe(this, Observer {
            it.results?.let {model ->

                adapter.updateAdapter(model)

                viewModel.finishLoading()
            }
        })

        viewModel.showYoutubeLiveData.observe(this, Observer {
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(it))
            try {
                this@MovieDetailActivity.startActivity(webIntent)
            } catch (ex: ActivityNotFoundException) {
            }
        })

    }

    override fun initViewModel() {

        var model : MoviesResponse.MoviesModel? = intent.getParcelableExtra("data")

        viewModel.init(model, model?.id!!)

        GlideManager.getInstance().LoadImageCenterCrop(this, BuildConfig.IMG_URL+model.poster_path, image_banner)

        viewModel.getMovies()
    }

    override fun initEvent() {
        adapter = ReviewAdapter(this,
            object : ReviewAdapter.OnAdapterScroll{
                override fun isLastPos() {
                    viewModel.getUserReview()
                }

            })
        rv_review.adapter = adapter
        rv_review.addItemDecoration(DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)))
        rv_review.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initLayout() {

    }
}

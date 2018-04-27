package com.arctouch.codechallenge.features.home.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.api.config.BuildConstants
import com.arctouch.codechallenge.application.ArcMovieApplication
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.databinding.ActivityDetailsBinding
import com.arctouch.codechallenge.di.component.DaggerDetailsActivityComponent
import com.arctouch.codechallenge.di.component.DetailsActivityComponent
import com.arctouch.codechallenge.domain.MovieDetails
import com.arctouch.codechallenge.features.constants.IntentConstants
import com.arctouch.codechallenge.features.home.contract.DetailsActivityContract
import com.arctouch.codechallenge.features.home.presenter.DetailsActivityPresenter
import com.squareup.picasso.Picasso

class DetailsActivity : BaseActivity<DetailsActivityContract.View, DetailsActivityPresenter,
        DetailsActivityComponent>(), DetailsActivityContract.View {

    private lateinit var binding : ActivityDetailsBinding
    private var movieId: Int? = null

    override fun createComponent(): DetailsActivityComponent =
            DaggerDetailsActivityComponent.builder()
                    .applicationComponent(ArcMovieApplication.component).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movieId = intent?.extras?.getInt(IntentConstants.DETAILS_INTENT_KEY)
        callLoadDetails(movieId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun callLoadDetails(id: Int?) {
        id?.let {
            presenter.loadDetails(it)
        }
    }

    override fun bindDetails(details: MovieDetails) {
        binding.detailsTitle.text = details.name
        binding.detailsReleaseValue.text = details.releaseDate
        binding.detailsOverviewValue.text = details.overview
        binding.detailsGenresValue.text = details.genres.map { it.name }.joinToString(", ")
        Picasso.with(this).load(BuildConstants.BASE_IMAGE_POSTER_URL + details.poster)
                .into(binding.detailsBanner);

    }

}

package com.arctouch.codechallenge.features.home.presenter

import android.util.Log
import com.arctouch.codechallenge.api.repository.HomeRepository
import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.domain.MovieDetails
import com.arctouch.codechallenge.features.home.contract.DetailsActivityContract
import javax.inject.Inject

class DetailsActivityPresenter
@Inject
constructor(private val repository: HomeRepository) : BasePresenter<DetailsActivityContract.View>(),
        DetailsActivityContract.Presenter<DetailsActivityContract.View> {

    override fun loadDetails(id: Int) {
        repository.getDetails(id)
                .subscribe({loadDetailsSuccess(it)}, {loadDetailsError(it)})
    }

    private fun loadDetailsSuccess(movieDetails : MovieDetails) {
        getView()?.bindDetails(movieDetails)
    }

    private fun loadDetailsError(throwable: Throwable) {
        Log.e("DetailsActivityPresent", throwable.message);
    }

}
package com.arctouch.codechallenge.features.home.contract

import com.arctouch.codechallenge.base.BaseContract
import com.arctouch.codechallenge.domain.MovieDetails

interface DetailsActivityContract {

    interface View : BaseContract.View {
        fun bindDetails(details: MovieDetails)
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun loadDetails(id: Int)
    }

    interface Component<V : View ,P : Presenter<V>> : BaseContract.Component<V,P>
}
package com.arctouch.codechallenge.features.home.contract

import com.arctouch.codechallenge.base.BaseContract
import com.arctouch.codechallenge.domain.UpcomingMovies

interface HomeActivityContract {

    interface View : BaseContract.View {
        fun bindMovies(movies : UpcomingMovies)
        fun bindSearch(movies : UpcomingMovies)
        fun emptySearch()
    }

    interface Presenter<V : View> : BaseContract.Presenter<V> {
        fun loadMovies()
        fun loadGenreList()
        fun searchMovies(query: String)
    }

    interface Component<V : View ,P : Presenter<V>> : BaseContract.Component<V,P>

}
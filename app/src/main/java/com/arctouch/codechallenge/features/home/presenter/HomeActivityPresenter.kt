package com.arctouch.codechallenge.features.home.presenter

import android.util.Log
import com.arctouch.codechallenge.api.repository.HomeRepository
import com.arctouch.codechallenge.api.repository.SharedPrefsManager
import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.domain.Genre
import com.arctouch.codechallenge.domain.Genres
import com.arctouch.codechallenge.domain.UpcomingMovies
import com.arctouch.codechallenge.features.home.contract.HomeActivityContract
import javax.inject.Inject

class HomeActivityPresenter
@Inject
constructor(private val repository: HomeRepository, private val sharedPrefsManager: SharedPrefsManager)
    : BasePresenter<HomeActivityContract.View>(), HomeActivityContract.Presenter<HomeActivityContract.View> {

    private var page : Int = 0

    override fun loadMovies() {
        repository.getMovies(++page)
                .subscribe({loadMoviesSuccess(it)}, {callError(it)})
    }

    override fun loadGenreList() {
        if (!sharedPrefsManager.hasRecords()) {
            repository.genreList()
                    .subscribe({ loadGenreSuccess(it) }, { callError(it) })
        }
    }

    override fun searchMovies(query: String) {
        repository.searchMovie(query)
                .subscribe({searchMoviesSuccess(it)}, {callError(it)})
    }

    private fun loadGenreSuccess(genreList: Genres) {
        genreList.genres.map {
            sharedPrefsManager.putValue(it.id.toString(), it.name)
        }
    }

    private fun searchMoviesSuccess(movies : UpcomingMovies) {
        movies.results?.let {
            movies.results.map { it.genres = translateGenre(it) }
            getView()?.bindSearch(movies)
        } ?: run {
            getView()?.emptySearch()
        }
    }

    private fun loadMoviesSuccess(movies : UpcomingMovies) {

        movies.results.map { it.genres = translateGenre(it) }
        getView()?.bindMovies(movies)
    }

    private fun translateGenre(movie: UpcomingMovies.UpcomingMovie) : List<Genre> {
        var genres : ArrayList<Genre> = ArrayList<Genre>()
        movie.genre.map{
            genres.add(Genre(it, sharedPrefsManager.getValue(it.toString())))
        }
        return genres
    }

    private fun callError(throwable: Throwable) {
        Log.e("HomeActivityPresent", throwable.message);
    }

}

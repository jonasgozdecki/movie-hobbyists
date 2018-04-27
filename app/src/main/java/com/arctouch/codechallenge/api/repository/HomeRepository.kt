package com.arctouch.codechallenge.api.repository

import com.arctouch.codechallenge.api.Endpoints
import com.arctouch.codechallenge.domain.Genres
import com.arctouch.codechallenge.domain.MovieDetails
import com.arctouch.codechallenge.domain.UpcomingMovies
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface HomeRepository {
    fun getMovies(page: Int): Observable<UpcomingMovies>
    fun getDetails(id: Int): Observable<MovieDetails>
    fun searchMovie(query: String): Observable<UpcomingMovies>
    fun genreList() : Observable<Genres>
}

class HomeRepositoryImp(private val endpoint: Endpoints)
    : HomeRepository {

    override fun getMovies(page: Int): Observable<UpcomingMovies> =
            endpoint
                .getUpcomingMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    override fun searchMovie(query: String): Observable<UpcomingMovies> =
            endpoint
                .searchMovies(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    override fun getDetails(id: Int): Observable<MovieDetails>  =
            endpoint
                .getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    override fun genreList(): Observable<Genres> =
            endpoint
                .genreList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

}
package com.arctouch.codechallenge.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.arctouch.codechallenge.application.ArcMovieApplication
import com.arctouch.codechallenge.di.scope.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

  @Singleton
  @Provides
  fun provideApplicationContext(): Context {
    return application
  }

  @Singleton
  @Provides
  fun provideGson(): Gson = GsonBuilder().setLenient().create()

  @Singleton
  @Provides
  fun provideSharedPreference() : SharedPreferences =
          application.getSharedPreferences("genrePref", Context.MODE_PRIVATE)

}
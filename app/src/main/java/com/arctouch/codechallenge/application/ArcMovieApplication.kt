package com.arctouch.codechallenge.application

import android.app.Application
import com.arctouch.codechallenge.api.repository.HomeRepository
import com.arctouch.codechallenge.di.component.ApplicationComponent
import com.arctouch.codechallenge.di.module.ApplicationModule
import com.arctouch.codechallenge.di.component.DaggerApplicationComponent

internal class ArcMovieApplication : Application() {

  companion object {
    @JvmStatic lateinit var component: ApplicationComponent
  }

  override fun onCreate() {
    super.onCreate()
    buildComponent()
  }

  private fun buildComponent() {
    component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
  }

}
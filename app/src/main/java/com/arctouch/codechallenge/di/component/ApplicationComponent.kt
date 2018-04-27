package com.arctouch.codechallenge.di.component

import android.content.Context
import com.arctouch.codechallenge.api.repository.HomeRepository
import com.arctouch.codechallenge.api.repository.SharedPrefsManager
import com.arctouch.codechallenge.di.module.ApplicationModule
import com.arctouch.codechallenge.di.module.HomeRepositoryModule
import com.arctouch.codechallenge.di.module.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ServiceModule::class, HomeRepositoryModule::class])
interface ApplicationComponent {
  val applicationContext: Context
  val homeRepository: HomeRepository
  val sharedPrefsManager : SharedPrefsManager
}
package com.arctouch.codechallenge.di.module

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.api.Endpoints
import com.arctouch.codechallenge.api.config.BuildConstants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            val url = addDefaultQueryParameters(request)
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        httpClient.networkInterceptors().add(interceptor)
        return httpClient.build()
    }

    private fun addDefaultQueryParameters(request: Request): HttpUrl? {
        return request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .addQueryParameter("language", BuildConstants.API_LANG)
                .build()
    }

    @Provides
    @Singleton
    fun providesAppApi(okHttpClient: OkHttpClient, gson: Gson): Endpoints {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConstants.BASE_API_URL)
                .client(okHttpClient)
                .build()

        return retrofit.create(Endpoints::class.java)
    }
}
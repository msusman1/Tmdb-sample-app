package com.tmdb.app.di

import com.tmdb.app.BuildConfig
import com.tmdb.app.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okhttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okhttpBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okhttpBuilder.addInterceptor(AuthInterceptor())
        return okhttpBuilder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(urlProvider.provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun providePeopleApi(retrofit: Retrofit): PeopleApi {
        return retrofit.create(PeopleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTvApi(retrofit: Retrofit): TvApi {
        return retrofit.create(TvApi::class.java)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}
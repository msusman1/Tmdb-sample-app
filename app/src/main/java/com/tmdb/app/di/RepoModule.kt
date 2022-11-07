package com.tmdb.app.di

import com.tmdb.app.data.movie.MovieRepositoryImpl
import com.tmdb.app.data.people.PeopleRepositoryImpl
import com.tmdb.app.data.tv.TvRepositoryImpl
import com.tmdb.app.domain.repository.movie.MovieRepository
import com.tmdb.app.domain.repository.people.PeopleRepository
import com.tmdb.app.domain.repository.tv.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepoModule {
    @Binds
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    fun provideTvRepository(tvRepositoryImpl: TvRepositoryImpl): TvRepository

    @Binds
    fun providePeopleRepository(peopleRepositoryImpl: PeopleRepositoryImpl): PeopleRepository
}
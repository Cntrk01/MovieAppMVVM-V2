package com.example.moviesearchappmvvm.hilt

import com.example.moviesearchappmvvm.network.Constants
import com.example.moviesearchappmvvm.network.MovieApiInterface
import com.example.moviesearchappmvvm.repository.MovieHomeRepo
import com.example.moviesearchappmvvm.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun createRetrofitInstance() : MovieApiInterface{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiInterface::class.java)
    }
    @Singleton
    @Provides
    fun provideRepository(movieInterface: MovieApiInterface):MovieHomeRepo{
        return MovieHomeRepo(movieInterface)
    }


}
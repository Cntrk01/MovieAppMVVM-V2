package com.example.moviesearchappmvvm.repository

import com.example.movieapppaging3mvmm.moviedetails.ui.data.moviedetail.MovieDetails
import com.example.moviesearchappmvvm.network.Constants
import com.example.moviesearchappmvvm.network.MovieApiInterface
import com.example.moviesearchappmvvm.util.Result
import com.example.moviesearchappmvvm.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MovieDetailRepo @Inject constructor(
    private val api: MovieApiInterface
) {

    suspend fun getMovieDetail(imdb:String) : Result<MovieDetails> {
        return try {
            val response=api.getMovieDetails(imdb, Constants.API_KEY)
            if(response.isSuccessful){
                Result(Status.SUCCESS,response.body(),null)
            }else{
                Result(Status.ERROR, null, null)
            }
        }
        catch (e:java.lang.Exception){
            Result(Status.ERROR, null, null)
        }
    }

}
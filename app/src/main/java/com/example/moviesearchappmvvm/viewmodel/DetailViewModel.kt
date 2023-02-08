package com.example.moviesearchappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapppaging3mvmm.moviedetails.ui.data.moviedetail.MovieDetails
import com.example.moviesearchappmvvm.network.Constants
import com.example.moviesearchappmvvm.network.MovieApiInterface
import com.example.moviesearchappmvvm.repository.MovieDetailRepo
import com.example.moviesearchappmvvm.util.Result
import com.example.moviesearchappmvvm.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieDetailRepo
) : ViewModel() {

    private val _movieDetail = MutableLiveData<Result<MovieDetails>>()
    val movieDetail:LiveData<Result<MovieDetails>> = _movieDetail

     fun getDetail(imdb:String){
        viewModelScope.launch {
            //Bu kod tıkladığı zaman önce loading işlemi gerçekleştirerek çökmesini engelliyor.
            _movieDetail.postValue(Result(Status.LOADING,null,null))
            _movieDetail.postValue(repository.getMovieDetail(imdb))
        }
    }



}
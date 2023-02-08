package com.example.moviesearchappmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.example.movieapppaging3mvmm.moviedetails.ui.data.Movie
import com.example.moviesearchappmvvm.Paging.MoviePaging
import com.example.moviesearchappmvvm.network.MovieApiInterface
import com.example.moviesearchappmvvm.repository.MovieHomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val repo: MovieHomeRepo
) : ViewModel() {

    var listenData :LiveData<PagingData<Movie>>

     init{
         //listenData değişkenine değeri yükledim.
         listenData=repo.dataTime().cachedIn(viewModelScope)
     }

    fun searchMovie(s: String) {
        repo.comeQuery(s)
    }




}
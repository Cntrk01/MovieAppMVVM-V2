package com.example.moviesearchappmvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.movieapppaging3mvmm.moviedetails.ui.data.Movie
import com.example.moviesearchappmvvm.Paging.MoviePaging
import com.example.moviesearchappmvvm.network.MovieApiInterface
import javax.inject.Inject


class MovieHomeRepo @Inject
constructor(private val api: MovieApiInterface) {

    private var data: LiveData<PagingData<Movie>>
    private var query=MutableLiveData<String>()

    init {
        data = MutableLiveData()
        //init ederek çalıştırdım.Çalışmak zorunda !!
        returnData()
    }

    //data değişkenine apiye attığımız isteklerin değerlerini yükledim.
    fun returnData(){
        data=query.switchMap {query->
            Pager(PagingConfig(pageSize = 10)){
                MoviePaging(query,api)
            }.liveData
        }
    }
    //Burdan da dönen değişkene erişim sağladım.Repodaki değişkenler private kaldı.
    fun dataTime() : LiveData<PagingData<Movie>>{
        return data
    }

    //Burda film ismini verip query değişkenine yükledim
    fun comeQuery(s:String){
        query.postValue(s)
    }

}
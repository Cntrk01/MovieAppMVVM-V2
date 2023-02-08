package com.example.moviesearchappmvvm.Paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapppaging3mvmm.moviedetails.ui.data.Movie
import com.example.moviesearchappmvvm.network.Constants
import com.example.moviesearchappmvvm.network.MovieApiInterface

class MoviePaging(val filmName:String,val movieApiInterface: MovieApiInterface) : PagingSource<Int,Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return  state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page=params.key ?: 1
        return try {
            val data=movieApiInterface.getAllData(filmName,page,Constants.API_KEY)
            Log.e("loadData",data.body()?.Search.toString())
            LoadResult.Page(
                data=data.body()?.Search!!,
                prevKey = if(page == 1) null else page-1,
                nextKey = if(data.body()?.Search?.isEmpty()!!) null else page+1
            )

        }catch (e:java.lang.Exception){
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}
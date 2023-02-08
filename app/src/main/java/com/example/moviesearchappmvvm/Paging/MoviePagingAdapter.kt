package com.example.moviesearchappmvvm.Paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapppaging3mvmm.moviedetails.ui.data.Movie
import com.example.moviesearchappmvvm.BR
import com.example.moviesearchappmvvm.databinding.ViewHolderBinding

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(diff_util) {


    var onClick: ((String) -> Unit)? = null

    companion object {
        val diff_util = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun onMovieClick(listener: (String) -> Unit) {
        onClick = listener
    }


    //bunu en yukarı yazdım BR.movie çıkmadı :D . buraya aldım çıktı !
    inner class MyViewHolder(val viewDataBinding: ViewHolderBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.viewDataBinding.setVariable(androidx.databinding.library.baseAdapters.BR,getItem(position))
        val data = getItem(position)
        holder.viewDataBinding.setVariable(BR.movie, data)
        holder.viewDataBinding.root.setOnClickListener {
            onClick?.let {
                it(data?.imdbID!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }
}
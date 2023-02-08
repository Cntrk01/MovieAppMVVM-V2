package com.example.moviesearchappmvvm.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
//String ifadesi ? olcak çünkü ilk tıkladığın zaman url direk gelmiyor ve çöküyor.
//Çünkü datanın gelme süresi yüklenme süresinden düşük olduğu için kod direk çalışıyor boş diyor ve çöküyor
//Ama böylelikle çökme önüne geçiyoruz.!
fun loadImage(imageView: ImageView,url:String?){
    url?.let {
        Glide.with(imageView).load(url).into(imageView)
    }
}
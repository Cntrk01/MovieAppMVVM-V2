package com.example.moviesearchappmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesearchappmvvm.util.Status
import com.example.moviesearchappmvvm.databinding.FragmentDetailBinding
import com.example.moviesearchappmvvm.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding?=null
    private val binding get() = _binding!!

    //başında val yerine var olduğu için viewModels kızdı :D
    private val detailViewModel : DetailViewModel by viewModels()
    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getDetail(args.imdbID)

        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }

        detailViewModel.movieDetail.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS-> {
                    binding.detailsProgress.visibility=View.GONE
                    binding.movieDetails=it.data
                }
                Status.ERROR->{
                    binding.detailsProgress.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.detailsProgress.visibility=View.VISIBLE
                }
            }
        }


    }

}
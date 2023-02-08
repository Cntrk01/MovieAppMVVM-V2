package com.example.moviesearchappmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesearchappmvvm.Paging.MoviePagingAdapter
import com.example.moviesearchappmvvm.R
import com.example.moviesearchappmvvm.databinding.FragmentHomeBinding
import com.example.moviesearchappmvvm.viewmodel.MovieHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding ?=null
    private val binding get() = _binding!!

    private val homeViewModel : MovieHomeViewModel by viewModels()
    private var movieAdapter=MoviePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter= MoviePagingAdapter()
        binding.movieRecycler.adapter=movieAdapter
        binding.movieRecycler.layoutManager=GridLayoutManager(requireContext(),2)

        movieAdapter.onMovieClick {
            val intent=HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(intent)
        }


        binding.movieSearch.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    homeViewModel.searchMovie(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
            homeViewModel.listenData.observe(requireActivity(), Observer {
                movieAdapter.submitData(lifecycle,it)
            })




    }
}
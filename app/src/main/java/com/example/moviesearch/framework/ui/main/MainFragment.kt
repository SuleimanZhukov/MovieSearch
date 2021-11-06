package com.example.moviesearch.framework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesearch.AppState
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentMainBinding
import com.example.moviesearch.framework.ui.details.DetailsFragment
import com.example.moviesearch.framework.ui.details.DetailsViewModel
import com.example.moviesearch.framework.ui.recyclerview.NowPlayingAdapter
import com.example.moviesearch.framework.ui.recyclerview.UpcomingAdapter
import com.example.moviesearch.model.entities.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private lateinit var upcomingAdapter: UpcomingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()

        binding.recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewUpcoming.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                nowPlayingAdapter.setMovies(appState.moviesData)
//                upcomingAdapter.setMovies(appState.moviesData)
                binding.recyclerViewNowPlaying.adapter = nowPlayingAdapter
//                binding.recyclerViewUpcoming.adapter = upcomingAdapter

            }
            is AppState.Loading -> {
                //Loading
            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initAdapters() {
        nowPlayingAdapter = NowPlayingAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(movie: Movie) {
                val bundle = Bundle().apply {
                    putParcelable(DetailsFragment.BUNDLE_EXTRA, movie)
                }
                activity?.supportFragmentManager!!.beginTransaction()
                    .add(R.id.mainContainer, DetailsFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        })
//        upcomingAdapter = UpcomingAdapter()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movie: Movie)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
package com.example.moviesearch.framework.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesearch.AppState
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentMainBinding
import com.example.moviesearch.framework.ui.recyclerview.NowPlayingAdapter
import com.example.moviesearch.framework.ui.recyclerview.NowPlayingCardView
import com.example.moviesearch.framework.ui.recyclerview.UpcomingAdapter
import com.example.moviesearch.framework.ui.recyclerview.UpcomingCardView

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var nowPlayingList = mutableListOf(
        NowPlayingCardView("Avengers", R.drawable.avengers_poster, "21/07/2022", "8.9"),
        NowPlayingCardView("Spider-Man", R.drawable.avengers_poster, "16/12/2016", "7.4"),
        NowPlayingCardView("Harry Potter", R.drawable.avengers_poster, "31/02/2019", "2.7"),
        NowPlayingCardView("Star Wars", R.drawable.avengers_poster, "21/07/2022", "5.8"),
        NowPlayingCardView("Knives Out", R.drawable.avengers_poster, "21/07/2022", "8.1"),
        NowPlayingCardView("The Twilight", R.drawable.avengers_poster, "21/07/2022", "0.0")
    )

    private var upcomingList = mutableListOf(
        UpcomingCardView("Ultron Strikes Back", R.drawable.avengers_poster, "21/07/2022"),
        UpcomingCardView("Zootopia", R.drawable.avengers_poster, "21/07/2022"),
        UpcomingCardView("Shrek", R.drawable.avengers_poster, "21/07/2022"),
        UpcomingCardView("Batman", R.drawable.avengers_poster, "21/07/2022")
    )

    private var nowPlayingAdapter = NowPlayingAdapter(nowPlayingList)
    private var upcomingAdapter = UpcomingAdapter(upcomingList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewNowPlaying.adapter = nowPlayingAdapter
        binding.recyclerViewUpcoming.adapter = upcomingAdapter
        binding.recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewUpcoming.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getMovies()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
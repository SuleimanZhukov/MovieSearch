package com.example.moviesearch.framework.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentMainBinding
import com.example.moviesearch.framework.ui.recyclerview.NowPlayingAdapter
import com.example.moviesearch.framework.ui.recyclerview.NowPlayingCardView
import com.example.moviesearch.framework.ui.recyclerview.UpcomingAdapter
import com.example.moviesearch.framework.ui.recyclerview.UpcomingCardView

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var nowPlayingList = mutableListOf(
            NowPlayingCardView("Avengers", 0, "21/07/2022", "8.9"),
            NowPlayingCardView("Spider-Man", 1, "16/12/2016", "7.4"),
            NowPlayingCardView("Harry Potter", 2, "31/02/2019", "2.7"),
            NowPlayingCardView("Star Wars", 3, "21/07/2022", "5.8"),
            NowPlayingCardView("Knives Out", 4, "21/07/2022", "8.1"),
            NowPlayingCardView("The Twilight", 5, "21/07/2022", "0.0")
        )

        var upcomingList = mutableListOf(
            UpcomingCardView("Ultron Strikes Back", 6, "21/07/2022"),
            UpcomingCardView("Zootopia", 7, "21/07/2022"),
            UpcomingCardView("Shrek", 8, "21/07/2022"),
            UpcomingCardView("Batman", 9, "21/07/2022")
        )

        var nowPlayingAdapter = NowPlayingAdapter(nowPlayingList)
        var upcomingAdapter = UpcomingAdapter(upcomingList)

        binding.recyclerViewNowPlaying.adapter = nowPlayingAdapter
        binding.recyclerViewUpcoming.adapter = upcomingAdapter
        binding.recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewUpcoming.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
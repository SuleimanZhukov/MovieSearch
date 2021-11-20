package com.example.moviesearch.framework.ui.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesearch.AppState
import com.example.moviesearch.databinding.FragmentDetailsBinding
import com.example.moviesearch.model.entities.Movie
import com.example.moviesearch.model.restentities.MovieDTO
import com.example.moviesearch.model.restentities.results
import org.koin.androidx.viewmodel.ext.android.viewModel

const val DETAILS_INTENT_FILTER = "DETAILS_INTENT_FILTER"
const val DETAILS_RUSSIAN_TITLE = "DETAILS_RUSSIAN_TITLE"
const val DETAILS_ORIGINAL_TITLE = "DETAILS_ORIGINAL_TITLE"
const val DETAILS_RELEASE_DATE = "DETAILS_RELEASE_DATE"
const val DETAILS_RATING = "DETAILS_RATING"
const val DETAILS_DESCRIPTION = "DETAILS_DESCRIPTION"

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            renderData(MovieDTO(results(
                intent.getStringExtra(DETAILS_RUSSIAN_TITLE),
                intent.getStringExtra(DETAILS_ORIGINAL_TITLE),
                intent.getStringExtra(DETAILS_RELEASE_DATE),
                intent.getStringExtra(DETAILS_RATING),
                intent.getStringExtra(DETAILS_DESCRIPTION)
            )))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
//        movie?.let {
//            with(binding) {
//                originalTitleDetails.text = it.originalTitle
//
//                viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
//                    when (appState) {
//                        is AppState.Error -> {
//                            //sda
//                        }
//                        is AppState.Loading -> {
//                            //sdf
//                        }
//                        is AppState.Success -> {
//                            russianTitleDetails.text = appState.moviesData[0].title
//                            originalTitleDetails.text = appState.moviesData[0].originalTitle
//                            releaseDateDetails.text = appState.moviesData[0].releaseDate
//                            ratingDetails.text = appState.moviesData[0].voteAverage.toString()
//                            description.text = appState.moviesData[0].overview
//                        }
//                    }
//                })
//                viewModel.loadData(it.originalTitle)
//            }
//        }
    }

    private fun renderData(movieDTO: MovieDTO) {
        val results = movieDTO.results

        val russianTitle = results[0]!!.title
        val originalTitle = results[0]!!.original_title
        val releaseDate = results[0]!!.release_date
        val rating = results[0]!!.vote_average.toString()
        val descriptionDetails = results[0]!!.overview

        with(binding) {
            russianTitleDetails.text = russianTitle
            originalTitleDetails.text = originalTitle
            releaseDateDetails.text = releaseDate
            ratingDetails.text = rating
            description.text = descriptionDetails
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "movie"

        fun newInstance(bundle: Bundle): DetailsFragment {
            var newFragment = DetailsFragment()
            newFragment.arguments = bundle
            return newFragment
        }
    }
}
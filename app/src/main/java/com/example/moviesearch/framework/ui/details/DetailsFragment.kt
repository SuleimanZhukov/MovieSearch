package com.example.moviesearch.framework.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesearch.AppState
import com.example.moviesearch.databinding.FragmentDetailsBinding
import com.example.moviesearch.model.entities.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val API_KEY = "9404872446d5cae8afbefd38b24ce36a"

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        movie?.let {
            with(binding) {
                originalTitleDetails.text = it.originalTitle

                viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            //sda
                        }
                        is AppState.Loading -> {
                            //sdf
                        }
                        is AppState.Success -> {
                            russianTitleDetails.text = appState.moviesData[0].title
                            originalTitleDetails.text = appState.moviesData[0].originalTitle
                            releaseDateDetails.text = appState.moviesData[0].releaseDate
                            ratingDetails.text = appState.moviesData[0].voteAverage.toString()
                            description.text = appState.moviesData[0].overview
                        }
                    }
                })
                viewModel.loadData(it.originalTitle)
            }
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
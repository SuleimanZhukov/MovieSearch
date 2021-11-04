package com.example.moviesearch.framework.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentDetailsBinding
import com.example.moviesearch.framework.ui.main.MainFragment

private const val API_KEY = "9404872446d5cae8afbefd38b24ce36a"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
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
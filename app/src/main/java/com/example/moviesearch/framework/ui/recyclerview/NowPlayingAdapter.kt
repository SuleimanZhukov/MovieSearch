package com.example.moviesearch.framework.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.databinding.CardViewNowPlayingBinding
import com.example.moviesearch.databinding.FragmentDetailsBinding
import com.example.moviesearch.model.entities.Movie

class NowPlayingAdapter(
    var nowPlayingCards: List<NowPlayingCardView>
) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    private var movieData: List<Movie> = listOf()

    private var _binding: CardViewNowPlayingBinding? = null
    private val binding get() = _binding!!

    fun setMovies(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(binding) {
            binding.cardViewNowPlayingTitle.text = movie.title
//            binding.cardViewNowPlayingImage = movie.poster
            binding.cardViewNowPlayingDate.text = movie.date
            binding.cardViewNowPlayingRating.text = movie.rating

            binding.cardViewNowPlayingImage.setOnClickListener {
                Toast.makeText(itemView.context, movie.title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        _binding = CardViewNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPlayingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return nowPlayingCards.size
    }
}
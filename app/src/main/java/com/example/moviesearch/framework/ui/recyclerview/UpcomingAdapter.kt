package com.example.moviesearch.framework.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R
import com.example.moviesearch.databinding.CardViewUpcomingBinding
import com.example.moviesearch.framework.ui.main.MainFragment
import com.example.moviesearch.model.entities.Movie

class UpcomingAdapter() : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private lateinit var binding: CardViewUpcomingBinding
    private var movieData: List<Movie> = listOf()

    fun setMovies(data: List<Movie>) {
        movieData = data
        notifyDataSetChanged()
    }

    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(binding) {
            binding.cardViewUpcomingTitle.text = movie.title
            binding.cardViewUpcomingDate.text = movie.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        binding = CardViewUpcomingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UpcomingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int {
        return movieData.size
    }
}
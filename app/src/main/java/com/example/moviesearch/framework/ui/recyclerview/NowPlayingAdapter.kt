package com.example.moviesearch.framework.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R

class NowPlayingAdapter(
    var nowPlayingCards: List<NowPlayingCardView>
) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById<TextView>(R.id.card_view_now_playing_title)
//        val poster: ImageView = itemView.findViewById<>(R.id.card_view_now_playing_image)
        val date: TextView = itemView.findViewById<TextView>(R.id.card_view_now_playing_date)
        val rating: TextView = itemView.findViewById<TextView>(R.id.card_view_now_playing_rating)

        fun bind(nowPlaying: NowPlayingCardView) {
            title.text = nowPlaying.title
//            poster.text = nowPlaying.poster
            date.text = nowPlaying.date
            rating.text = nowPlaying.rating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_now_playing, parent, false)
        return NowPlayingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.title.text = nowPlayingCards[position].title
        holder.date.text = nowPlayingCards[position].date
        holder.rating.text = nowPlayingCards[position].rating
    }

    override fun getItemCount(): Int {
        return nowPlayingCards.size
    }
}
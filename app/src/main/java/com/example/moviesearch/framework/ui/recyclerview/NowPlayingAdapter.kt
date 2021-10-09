package com.example.moviesearch.framework.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R

class NowPlayingAdapter(
    var nowPlayingCards: List<NowPlayingCardView>
) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_now_playing, parent, false)
        return NowPlayingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.itemView.apply {

        }
    }

    override fun getItemCount(): Int {
        return nowPlayingCards.size
    }
}
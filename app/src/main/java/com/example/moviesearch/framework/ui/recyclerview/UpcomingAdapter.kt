package com.example.moviesearch.framework.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearch.R

class UpcomingAdapter(
    var upcomingCards: List<UpcomingCardView>
) : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_upcoming, parent, false)
        return UpcomingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.itemView.apply {
            
        }
    }

    override fun getItemCount(): Int {
        return upcomingCards.size
    }
}
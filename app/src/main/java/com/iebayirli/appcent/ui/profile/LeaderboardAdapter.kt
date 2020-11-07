package com.iebayirli.appcent.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iebayirli.appcent.R
import com.iebayirli.appcent.model.User
import kotlinx.android.synthetic.main.item_leaderboard.view.*

class LeaderboardAdapter(private val items: List<User>, private val userId: String) :
    RecyclerView.Adapter<LeaderboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        return LeaderboardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_leaderboard, parent, false)
        )
    }


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(items[position], position, userId)
    }
}

class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User, position: Int, userId: String) {
        itemView.tvName.text = "${position + 1}. ${user.name}"
        itemView.tvPoint.text = "${user.point} Puan"
        if (userId == user.uid) {
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.icon_color
                )
            )
        }

    }
}
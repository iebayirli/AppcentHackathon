package com.iebayirli.appcent.ui.achievements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.vipulasri.timelineview.TimelineView
import com.github.vipulasri.timelineview.TimelineView.LineStyle.DASHED
import com.github.vipulasri.timelineview.TimelineView.LineStyle.NORMAL
import com.iebayirli.appcent.R
import com.iebayirli.appcent.model.Achievement
import kotlinx.android.synthetic.main.item_achievements.view.*


class AchievementsAdapter(private val items: List<Achievement>) :
    RecyclerView.Adapter<AchievementsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsViewHolder {
        return AchievementsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_achievements, parent, false), viewType
        )
    }

    override fun getItemViewType(position: Int) = TimelineView.getTimeLineViewType(
        position,
        itemCount
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AchievementsViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class AchievementsViewHolder(itemView: View, val viewType: Int) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(achievement: Achievement) {
        itemView.timeline.initLine(viewType)
        itemView.alpha = if (achievement.isRewarded) 1f else 0.3f
        itemView.timeline.setStartLineColor(
            if (achievement.isRewarded) R.color.purple_700 else R.color.icon_color,
            viewType
        )
        itemView.timeline.setMarkerColor(if (achievement.isRewarded) R.color.purple_700 else R.color.icon_color)
        itemView.timeline.setEndLineColor(
            if (achievement.isRewarded) R.color.purple_700 else R.color.icon_color,
            viewType
        )
        itemView.timeline.lineStyle = if (achievement.isRewarded) NORMAL else DASHED
        itemView.text_timeline_title.text = achievement.name + " - " + achievement.limit + " Puan"
        Glide.with(itemView.context).load(achievement.icon).into(itemView.text_timeline_date)
    }
}
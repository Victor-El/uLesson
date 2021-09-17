package me.codeenzyme.ulesson.livelessons.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.codeenzyme.data.models.LiveLessonsModel
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.ItemLiveLessonsBinding

class LiveLessonsRecyclerAdapter( private val callback: (LiveLessonsModel.Data) -> Unit) : ListAdapter<LiveLessonsModel.Data, LiveLessonsRecyclerAdapter.LiveLessonViewHolder>(LiveLessonDiffUtilItemCallback){

    class LiveLessonViewHolder(viewBinding: ItemLiveLessonsBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: LiveLessonsModel.Data, func: (LiveLessonsModel.Data) -> Unit) {
            val binding = ItemLiveLessonsBinding.bind(itemView)
            binding.root.setOnClickListener { func(data) }
            binding.let {
                Glide.with(it.root).load(data.imageUrl).into(it.liveLessonsImage)
                it.subject.text = data.subject.name
                it.topic.text = data.topic
                it.tutorName.text = "${data.tutor.firstname} ${data.tutor.lastname}}"
                it.badge.setImageResource(when (data.status) {
                    "upcoming" -> {
                        R.drawable.ic_upcoming_badge
                    }
                    "live" -> {
                        R.drawable.ic_live_badge
                    }
                    "replay" -> {
                        R.drawable.ic_replay_badge
                    }
                    else -> 0
                })
            }
        }
    }

    object LiveLessonDiffUtilItemCallback: DiffUtil.ItemCallback<LiveLessonsModel.Data>() {
        override fun areItemsTheSame(
            oldItem: LiveLessonsModel.Data,
            newItem: LiveLessonsModel.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LiveLessonsModel.Data,
            newItem: LiveLessonsModel.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveLessonViewHolder {
        val viewBinding = ItemLiveLessonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LiveLessonViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: LiveLessonViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }

}
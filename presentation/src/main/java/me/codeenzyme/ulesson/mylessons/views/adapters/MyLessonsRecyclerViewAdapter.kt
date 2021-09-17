package me.codeenzyme.ulesson.mylessons.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.codeenzyme.domain.models.MyLessonsModel
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.ItemMyLessonsBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat

class MyLessonsRecyclerViewAdapter: ListAdapter<MyLessonsModel.Data, MyLessonsRecyclerViewAdapter.MyLessonViewHolder>(MyLessonDiffUtilItemCallback) {

    class MyLessonViewHolder(viewBinding: ItemMyLessonsBinding): RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: MyLessonsModel.Data) {
            val binding = ItemMyLessonsBinding.bind(itemView)
            Glide.with(binding.root).load(data.imageUrl).into(binding.image)
            binding.let {
                it.topic.text = data.topic
                it.subject.text = data.subject.name
                when (data.status) {
                    "upcoming" -> {
                        it.badge.setImageResource(R.drawable.ic_upcoming_badge)
                    }
                    "live" -> {
                        it.badge.setImageResource(R.drawable.ic_live_badge)
                    }
                    "replay" -> {
                        it.badge.setImageResource(R.drawable.ic_replay_badge)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLessonViewHolder {
        val view = ItemMyLessonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyLessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object MyLessonDiffUtilItemCallback: DiffUtil.ItemCallback<MyLessonsModel.Data>() {
    override fun areItemsTheSame(
        oldItem: MyLessonsModel.Data,
        newItem: MyLessonsModel.Data
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MyLessonsModel.Data,
        newItem: MyLessonsModel.Data
    ): Boolean {
        return oldItem == newItem
    }

}
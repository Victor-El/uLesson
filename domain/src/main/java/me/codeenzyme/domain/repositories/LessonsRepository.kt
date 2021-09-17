package me.codeenzyme.domain.repositories

import androidx.lifecycle.LiveData
import me.codeenzyme.domain.models.MyLessonsModel

interface LessonsRepository {
    fun getMyLessons(): LiveData<MyLessonsModel>
}
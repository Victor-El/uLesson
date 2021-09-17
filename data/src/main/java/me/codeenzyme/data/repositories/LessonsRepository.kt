package me.codeenzyme.data.repositories

import androidx.lifecycle.LiveData
import me.codeenzyme.data.models.MyLessonsModel


interface LessonsRepository {
    suspend fun getMyLessons(): LiveData<MyLessonsModel>
}
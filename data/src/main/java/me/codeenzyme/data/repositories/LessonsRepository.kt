package me.codeenzyme.data.repositories

import androidx.lifecycle.LiveData
import me.codeenzyme.data.models.LiveLessonsModel
import me.codeenzyme.data.models.MyLessonsModel
import me.codeenzyme.data.models.PromotedModel
import me.codeenzyme.data.models.Resource


interface LessonsRepository {
    suspend fun getMyLessons(): LiveData<MyLessonsModel>
    suspend fun fetchLiveLessons(): Resource<LiveLessonsModel>
    suspend fun fetchPromoted(): Resource<PromotedModel>
}
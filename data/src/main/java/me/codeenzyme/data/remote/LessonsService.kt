package me.codeenzyme.data.remote

import me.codeenzyme.data.models.LiveLessonsModel
import me.codeenzyme.data.models.MyLessonsModel
import me.codeenzyme.data.models.PromotedModel
import retrofit2.http.GET

interface LessonsService {

    @GET("lessons/me")
    suspend fun getMyLessons(): MyLessonsModel

    @GET("lessons")
    suspend fun getLiveLessons(): LiveLessonsModel

    @GET("promoted")
    suspend fun getPromotedLessons(): PromotedModel
}
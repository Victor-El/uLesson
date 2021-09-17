package me.codeenzyme.data.remote

import me.codeenzyme.data.models.MyLessonsModel
import retrofit2.http.GET

interface LessonsService {

    @GET("lessons/me")
    suspend fun getMyLessons(): MyLessonsModel
}
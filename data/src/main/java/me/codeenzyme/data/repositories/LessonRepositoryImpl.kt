package me.codeenzyme.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.codeenzyme.data.database.AppDatabase
import me.codeenzyme.data.dtos.toMyLessonModelData
import me.codeenzyme.data.entities.Lesson
import me.codeenzyme.data.models.MyLessonsModel
import me.codeenzyme.data.remote.LessonsService
import java.lang.Exception
import javax.inject.Inject

class LessonRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val lessonsService: LessonsService
): LessonsRepository {
    override suspend fun getMyLessons(): LiveData<MyLessonsModel> {
        var success = true
        try {
            val result = lessonsService.getMyLessons()
            withContext(Dispatchers.IO) {
                appDatabase.lessonDao().clearAll()
                appDatabase.lessonDao().insertAll(result.data.map { Lesson(
                    createdAt = it.createdAt,
                    expiresAt = it.expiresAt,
                    id = it.id,
                    imageUrl = it.imageUrl,
                    startAt = it.startAt,
                    status = it.status,
                    subject = Lesson.Subject(it.subject.name),
                    topic = it.topic,
                    tutor = Lesson.Tutor(firstname = it.tutor.firstname, lastname = it.tutor.lastname ?: "null")
                ) })
            }
        } catch (e: Exception) {
            Log.d("ELEZUA", e.localizedMessage)
            success = false
        }
        return Transformations.map(appDatabase.lessonDao().observeAll()) {
            MyLessonsModel(success = success, data = it.map { value -> value.toMyLessonModelData() })
        }
    }
}
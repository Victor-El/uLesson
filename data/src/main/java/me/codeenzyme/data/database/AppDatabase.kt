package me.codeenzyme.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.codeenzyme.data.daos.LessonDao
import me.codeenzyme.data.entities.Lesson

@Database(entities = [Lesson::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}
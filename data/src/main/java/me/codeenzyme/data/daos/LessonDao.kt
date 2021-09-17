package me.codeenzyme.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.codeenzyme.data.entities.Lesson

@Dao
interface LessonDao {

    @Query("SELECT * FROM lesson")
    fun getAll(): List<Lesson>

    @Insert
    fun insertAll(vararg lesson: Lesson)

    @Insert
    fun insertAll(lesson: List<Lesson>)

    @Delete
    fun delete(lesson: Lesson)

    @Query("SELECT * FROM lesson")
    fun observeAll(): LiveData<List<Lesson>>

    @Query("DELETE FROM lesson")
    fun clearAll()

}
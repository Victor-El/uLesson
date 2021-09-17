package me.codeenzyme.data.dtos

import me.codeenzyme.data.entities.Lesson
import me.codeenzyme.data.models.MyLessonsModel


fun Lesson.toMyLessonModelData(): MyLessonsModel.Data {
    return MyLessonsModel.Data(
        createdAt = this.createdAt,
        expiresAt = this.expiresAt,
        id = this.id,
        imageUrl = this.imageUrl,
        startAt = this.startAt,
        status = this.status,
        subject = MyLessonsModel.Data.Subject(this.subject.name),
        topic = this.topic,
        tutor = MyLessonsModel.Data.Tutor(firstname = this.tutor.firstname, lastname = this.tutor.lastname)
    )
}
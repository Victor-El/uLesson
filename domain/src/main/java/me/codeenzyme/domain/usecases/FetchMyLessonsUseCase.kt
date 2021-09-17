package me.codeenzyme.domain.usecases

import me.codeenzyme.data.repositories.LessonsRepository
import javax.inject.Inject

class FetchMyLessonsUseCase @Inject constructor(
    private val repository: LessonsRepository
){
    suspend operator fun invoke() = repository.getMyLessons()
}
package me.codeenzyme.domain.usecases

import me.codeenzyme.data.models.LiveLessonsModel
import me.codeenzyme.data.models.PromotedModel
import me.codeenzyme.data.models.Resource
import me.codeenzyme.data.repositories.LessonsRepository
import javax.inject.Inject

class FetchPromotedAndLiveLessonsUseCase @Inject constructor(
    private val repository: LessonsRepository
) {
    suspend operator fun invoke(): PromotedAndLive {
        val promoted = repository.fetchPromoted()
        val live = repository.fetchLiveLessons()
        return PromotedAndLive(live, promoted)
    }

    data class PromotedAndLive(
        val live: Resource<LiveLessonsModel>,
        val promoted: Resource<PromotedModel>
    ) {
        fun complete(): Boolean {
            var count = 0
            when (live) {
                is Resource.Error -> {}
                is Resource.Success -> count++
            }
            when (promoted) {
                is Resource.Error -> {}
                is Resource.Success -> count++
            }
            return count == 2
        }
    }
}
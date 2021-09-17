package me.codeenzyme.ulesson.livelessons.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.codeenzyme.data.models.LiveLessonsModel
import me.codeenzyme.domain.usecases.FetchPromotedAndLiveLessonsUseCase
import javax.inject.Inject

@HiltViewModel
class LiveLessonsViewModel @Inject constructor(
    private val fetchPromotedAndLiveLessonsUseCase: FetchPromotedAndLiveLessonsUseCase
): ViewModel() {

    private val subjectsFirstVal = "ALL SUBJECTS"
    val subjects = mutableListOf(subjectsFirstVal)

    private val subjectsLiveData = MutableLiveData<List<String>>()
    fun observeSubjects(): LiveData<List<String>> = subjectsLiveData

    fun submitSubjects(data: LiveLessonsModel) {
        val result = mutableListOf<String>(subjectsFirstVal,)
        result.addAll(1, data.data.map { it.subject.name })
        subjectsLiveData.value = result
    }

    suspend fun getLiveAndPromoted(): FetchPromotedAndLiveLessonsUseCase.PromotedAndLive {
        return fetchPromotedAndLiveLessonsUseCase()
    }
}
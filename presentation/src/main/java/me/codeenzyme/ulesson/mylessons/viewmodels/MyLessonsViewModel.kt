package me.codeenzyme.ulesson.mylessons.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.codeenzyme.data.models.MyLessonsModel
import me.codeenzyme.domain.usecases.FetchMyLessonsUseCase
import me.codeenzyme.domain.utils.DataUtil
import javax.inject.Inject

@HiltViewModel
class MyLessonsViewModel @Inject constructor(
    private val fetchMyLessonsUseCase: FetchMyLessonsUseCase
) : ViewModel() {
    private val subjectsFirstVal = "ALL SUBJECTS"
    val subjects = mutableListOf(subjectsFirstVal)

    private val myLessonsLiveData = MutableLiveData<MyLessonsModel>()

    private val subjectsLiveData = MutableLiveData<List<String>>()

    fun observeSubjects(): LiveData<List<String>> {
        return subjectsLiveData
    }

    fun setSubjects(data: MyLessonsModel) {
        val newSubjects = mutableListOf<String>(subjectsFirstVal)
        newSubjects.addAll(1, data.data.map { it.subject.name })
        subjectsLiveData.value = newSubjects
    }

    fun getMyLessons(): LiveData<MyLessonsModel> {
        viewModelScope.launch {
            delay(1500)
            val result = DataUtil.getMyLessons();
            myLessonsLiveData.value = result
            val newSubjects = mutableListOf<String>(subjectsFirstVal)
            newSubjects.addAll(1, result.data.map { it.subject.name })
            subjectsLiveData.value = newSubjects
        }
        return myLessonsLiveData
    }

    suspend fun fetchMyLessons(): LiveData<MyLessonsModel> {
        return fetchMyLessonsUseCase()
    }
}
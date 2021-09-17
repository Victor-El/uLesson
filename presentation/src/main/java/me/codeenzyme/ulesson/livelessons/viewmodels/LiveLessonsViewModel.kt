package me.codeenzyme.ulesson.livelessons.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveLessonsViewModel @Inject constructor(): ViewModel() {
    val subjects = mutableListOf("ALL SUBJECTS",)
}
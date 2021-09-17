package me.codeenzyme.ulesson.mylessons.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyLessonsViewModel @Inject constructor(
) : ViewModel() {
    val subjects = mutableListOf("ALL SUBJECTS",)
}
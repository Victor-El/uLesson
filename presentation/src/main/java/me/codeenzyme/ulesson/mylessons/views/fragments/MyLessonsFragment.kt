package me.codeenzyme.ulesson.mylessons.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentMyLessonsBinding
import me.codeenzyme.ulesson.mylessons.viewmodels.MyLessonsViewModel

@AndroidEntryPoint
class MyLessonsFragment: Fragment(R.layout.fragment_my_lessons) {

    private var viewBinding: FragmentMyLessonsBinding? = null

    private val viewModel by viewModels<MyLessonsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMyLessonsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
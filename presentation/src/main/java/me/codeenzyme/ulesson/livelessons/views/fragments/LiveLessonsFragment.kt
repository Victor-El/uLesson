package me.codeenzyme.ulesson.livelessons.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentLiveLessonsBinding
import me.codeenzyme.ulesson.livelessons.viewmodels.LiveLessonsViewModel

@AndroidEntryPoint
class LiveLessonsFragment: Fragment(R.layout.fragment_live_lessons) {

    private val viewModel by viewModels<LiveLessonsViewModel>()
    private var viewBinding: FragmentLiveLessonsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentLiveLessonsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}
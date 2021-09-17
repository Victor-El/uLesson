package me.codeenzyme.ulesson.livelessons.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentLiveLessonsBinding
import me.codeenzyme.ulesson.livelessons.viewmodels.LiveLessonsViewModel

@AndroidEntryPoint
class LiveLessonsFragment: Fragment(R.layout.fragment_live_lessons) {

    private val viewModel by viewModels<LiveLessonsViewModel>()
    private var viewBinding: FragmentLiveLessonsBinding? = null

    private lateinit var subjectsArrayAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentLiveLessonsBinding.bind(view)

        subjectsArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.layout_spinner_item, viewModel.subjects)
        subjectsArrayAdapter.setDropDownViewResource(R.layout.item_layout_spinner_dropdown)

        viewBinding?.let {
            it.subjectSpinner.adapter = subjectsArrayAdapter

            it.backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            it.fab.setOnClickListener {
                findNavController().navigate(LiveLessonsFragmentDirections.actionLiveLessonsFragmentToMyLessonsFragment())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}
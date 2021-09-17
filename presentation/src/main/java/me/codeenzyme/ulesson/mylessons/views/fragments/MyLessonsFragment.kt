package me.codeenzyme.ulesson.mylessons.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentMyLessonsBinding
import me.codeenzyme.ulesson.mylessons.viewmodels.MyLessonsViewModel

@AndroidEntryPoint
class MyLessonsFragment : Fragment(R.layout.fragment_my_lessons) {

    private var viewBinding: FragmentMyLessonsBinding? = null

    private val viewModel by viewModels<MyLessonsViewModel>()

    private lateinit var subjectsArrayAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMyLessonsBinding.bind(view)
        subjectsArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.layout_spinner_item, viewModel.subjects)
        subjectsArrayAdapter.setDropDownViewResource(R.layout.item_layout_spinner_dropdown)
        viewBinding?.let {
            it.subjectSpinner.adapter = subjectsArrayAdapter
            it.addLiveLessons.clipToOutline = true

            it.addLiveLessons.setOnClickListener {
                findNavController().navigate(MyLessonsFragmentDirections
                    .actionMyLessonsFragmentToLiveLessonsFragment())
            }

            it.backButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
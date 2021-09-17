package me.codeenzyme.ulesson.mylessons.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentMyLessonsBinding
import me.codeenzyme.ulesson.mylessons.viewmodels.MyLessonsViewModel
import me.codeenzyme.ulesson.mylessons.views.adapters.MyLessonsRecyclerViewAdapter

@AndroidEntryPoint
class MyLessonsFragment : Fragment(R.layout.fragment_my_lessons) {

    private var viewBinding: FragmentMyLessonsBinding? = null

    private val viewModel by viewModels<MyLessonsViewModel>()

    private lateinit var subjectsArrayAdapter: ArrayAdapter<String>

    private lateinit var myLessonsAdapter: MyLessonsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMyLessonsBinding.bind(view)
        subjectsArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.layout_spinner_item, viewModel.subjects)
        subjectsArrayAdapter.setDropDownViewResource(R.layout.item_layout_spinner_dropdown)

        myLessonsAdapter = MyLessonsRecyclerViewAdapter()

        viewBinding?.let {
            it.myLessonsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.myLessonsRecyclerView.adapter = myLessonsAdapter

            it.subjectSpinner.adapter = subjectsArrayAdapter
            it.addLiveLessons.clipToOutline = true

            it.addLiveLessons.setOnClickListener {
                findNavController().navigate(
                    MyLessonsFragmentDirections
                        .actionMyLessonsFragmentToLiveLessonsFragment()
                )
            }

            it.backButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        startObservers()
    }

    private fun startObservers() {
        viewModel.observeSubjects().observe(viewLifecycleOwner) {
            subjectsArrayAdapter.clear()
            subjectsArrayAdapter.addAll(it)
            subjectsArrayAdapter.notifyDataSetChanged()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.fetchMyLessons().observe(viewLifecycleOwner) {
                viewModel.setSubjects(it)
                if (it.success) {
                    viewBinding?.run {
                        loadingStateView.isVisible = false
                        dataStateView.isVisible = it.data.isNotEmpty()
                        emptyStateView.isVisible = it.data.isEmpty()
                    }
                    myLessonsAdapter.submitList(it.data)
                } else {
                    viewBinding?.run {
                        loadingStateView.isVisible = false
                        dataStateView.isVisible = it.data.isNotEmpty()
                        emptyStateView.isVisible = it.data.isEmpty()
                    }
                    myLessonsAdapter.submitList(it.data)

                    val snackbar = Snackbar.make(viewBinding!!.root, "Network error", LENGTH_INDEFINITE)
                    snackbar.setBackgroundTint(resources.getColor(R.color.color_orange_dark))
                    snackbar.setAction("Cancel") {
                        snackbar.dismiss()
                    }
                    snackbar.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
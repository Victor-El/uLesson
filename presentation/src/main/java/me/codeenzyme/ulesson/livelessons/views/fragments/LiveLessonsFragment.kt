package me.codeenzyme.ulesson.livelessons.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.data.models.LiveLessonsModel
import me.codeenzyme.data.models.PromotedModel
import me.codeenzyme.data.models.Resource
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentLiveLessonsBinding
import me.codeenzyme.ulesson.databinding.ItemLayoutPromotedBinding
import me.codeenzyme.ulesson.livelessons.viewmodels.LiveLessonsViewModel
import me.codeenzyme.ulesson.livelessons.views.adapters.LiveLessonsRecyclerAdapter

@AndroidEntryPoint
class LiveLessonsFragment: Fragment(R.layout.fragment_live_lessons) {

    private val viewModel by viewModels<LiveLessonsViewModel>()
    private var viewBinding: FragmentLiveLessonsBinding? = null

    private lateinit var subjectsArrayAdapter: ArrayAdapter<String>

    private lateinit var liveLessonsRecyclerAdapter: LiveLessonsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentLiveLessonsBinding.bind(view)

        subjectsArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.layout_spinner_item, viewModel.subjects)
        subjectsArrayAdapter.setDropDownViewResource(R.layout.item_layout_spinner_dropdown)

        liveLessonsRecyclerAdapter = LiveLessonsRecyclerAdapter {
            Snackbar.make(viewBinding!!.root, it.topic, Snackbar.LENGTH_SHORT).show()
        }

        viewBinding?.let {
            it.subjectSpinner.adapter = subjectsArrayAdapter

            it.liveLessons.adapter = liveLessonsRecyclerAdapter
            it.liveLessons.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            it.backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            it.fab.setOnClickListener {
                findNavController().navigate(LiveLessonsFragmentDirections.actionLiveLessonsFragmentToMyLessonsFragment())
            }
        }

        startObservers()
    }

    private fun startObservers() {
        viewModel.observeSubjects().observe(viewLifecycleOwner) {
            subjectsArrayAdapter.run {
                clear()
                addAll(it)
                notifyDataSetChanged()
            }
        }



        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            val result = viewModel.getLiveAndPromoted()
            if (result.complete()) {
                when(result.live) {
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        val liveResult = result.live as Resource.Success<LiveLessonsModel>
                        val promotedResult = result.promoted as Resource.Success<PromotedModel>
                        viewModel.submitSubjects(liveResult.data)
                        if (liveResult.data.success) {
                            if (liveResult.data.data.isNotEmpty()) {
                                viewBinding?.let {
                                    it.carouselView.isVisible = true
                                    it.carouselLoading.root.isVisible = false
                                    it.dataStateView.isVisible = true
                                    it.loadingStateViewStateView.isVisible = false

                                    liveLessonsRecyclerAdapter.submitList(liveResult.data.data)

                                    it.carouselView.apply {
                                        size = promotedResult.data.data.size
                                        resource = R.layout.item_layout_promoted
                                        autoPlay = true
                                        indicatorAnimationType = IndicatorAnimationType.THIN_WORM
                                        carouselOffset = OffsetType.CENTER
                                        setCarouselViewListener { view, position ->
                                            // Example here is setting up a full image carousel
                                            val binding = ItemLayoutPromotedBinding.bind(view)
                                            Glide.with(requireContext()).load(promotedResult.data.data[position].imageUrl).into(binding.image)
                                            binding.let { b ->
                                                val data = promotedResult.data.data
                                                b.topicTitle.text = data[position].topic
                                                b.badge.setImageResource(when (data[position].status) {
                                                    "upcoming" -> {
                                                        R.drawable.ic_upcoming_badge
                                                    }
                                                    "live" -> {
                                                        R.drawable.ic_live_badge
                                                    }
                                                    "replay" -> {
                                                        R.drawable.ic_replay_badge
                                                    }
                                                    else -> 0
                                                })
                                            }
                                        }
                                        show()
                                    }
                                }
                            } else {
                                viewBinding?.let {
                                    it.carouselView.isVisible = true
                                    it.carouselLoading.root.isVisible = false
                                    it.emptyStateView.isVisible = true
                                    it.loadingStateViewStateView.isVisible = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}
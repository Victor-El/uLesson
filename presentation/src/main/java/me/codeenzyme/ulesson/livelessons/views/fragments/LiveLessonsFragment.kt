package me.codeenzyme.ulesson.livelessons.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import dagger.hilt.android.AndroidEntryPoint
import me.codeenzyme.ulesson.R
import me.codeenzyme.ulesson.databinding.FragmentLiveLessonsBinding
import me.codeenzyme.ulesson.databinding.ItemLayoutPromotedBinding
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

            it.carouselView.apply {
                size = 5
                resource = R.layout.item_layout_promoted
                autoPlay = true
                indicatorAnimationType = IndicatorAnimationType.THIN_WORM
                carouselOffset = OffsetType.CENTER
                setCarouselViewListener { view, position ->
                    // Example here is setting up a full image carousel
                    val binding = ItemLayoutPromotedBinding.bind(view)
                    binding.image.setImageDrawable(resources.getDrawable(R.drawable.test_image))
                }
                // After you finish setting up, show the CarouselView
                show()
            }

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
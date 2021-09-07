package com.example.facerecognitionapp.ui.details

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import com.example.facerecognitionapp.R
import com.example.facerecognitionapp.base.BaseViewModelFragment
import com.example.facerecognitionapp.databinding.FragmentDetailsBinding
import kotlin.reflect.KClass

class DetailsFragment: BaseViewModelFragment<DetailsViewModel, FragmentDetailsBinding>() {
    override fun viewModelClass(): KClass<DetailsViewModel> = DetailsViewModel::class

    override fun layout(): Int = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imageRes = DetailsFragmentArgs.fromBundle(requireArguments()).imageRes

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        vm.setImageRes(imageRes)

        vm.rects.observe(viewLifecycleOwner) { rects ->
            binding.image.setRect(rects.map {
                Rect(it.left, it.top, it.right, it.bottom)
            })
        }

        binding.image.doOnLayout {
            val targetWidth = it.width
            val targetHeight = it.height
            vm.calculateFace(targetWidth, targetHeight, imageRes)
        }
    }
}
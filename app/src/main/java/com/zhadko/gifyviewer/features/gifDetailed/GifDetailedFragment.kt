package com.zhadko.gifyviewer.features.gifDetailed

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.zhadko.gifyviewer.R
import com.zhadko.gifyviewer.base.BaseFragment
import com.zhadko.gifyviewer.databinding.FragmentGifDetailedBinding
import com.zhadko.gifyviewer.extensions.loadAsGif

class GifDetailedFragment :
    BaseFragment<FragmentGifDetailedBinding>(FragmentGifDetailedBinding::inflate) {

    private val detailedArgs by navArgs<GifDetailedFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            gifTitle.text = detailedArgs.gif.title
            Glide.with(requireContext()).loadAsGif(
                detailedArgs.gif.path,
                binding.gifImage,
                R.drawable.ic_launcher_background
            )
        }
    }
}
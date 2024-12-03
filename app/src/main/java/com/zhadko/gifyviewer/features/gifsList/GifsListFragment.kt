package com.zhadko.gifyviewer.features.gifsList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhadko.gifyviewer.base.BaseFragment
import com.zhadko.gifyviewer.databinding.FragmentGifsListBinding
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.extensions.collectOnLifeCycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GifsListFragment : BaseFragment<FragmentGifsListBinding>(FragmentGifsListBinding::inflate) {

    private val viewModel by viewModel<GifsListViewModel>()
    private val gifsAdapter by lazy {
        GifsAdapter { gif ->
            navigateDetails(gif)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        with(binding) {
            gifsList.adapter = gifsAdapter
            val manager = LinearLayoutManager(
                requireContext()
            )
            gifsList.layoutManager = manager
        }
    }

    private fun setupObservers() {
        collectOnLifeCycle(viewModel.state, ::handleStateScreen)
        collectOnLifeCycle(viewModel.items, ::handleSuccess)
    }

    private fun handleStateScreen(state: GifListStates) {
        when (state) {
            GifListStates.EmptyGifsList -> handleEmptyGifsList()
            is GifListStates.Error -> {}
            GifListStates.Loading -> handleLoadingState()
        }
    }

    private fun handleLoadingState() {
        with(binding) {
            progressBar.isVisible = true
            gifsList.isVisible = false
            noData.isVisible = false
        }
    }

    private fun handleEmptyGifsList() {
        with(binding) {
            progressBar.isVisible = false
            gifsList.isVisible = false
            noData.isVisible = true
        }
    }

    private fun handleSuccess(gifsListData: PagingData<Gif>) {
        with(binding) {
            progressBar.isVisible = false
            noData.isVisible = false
            gifsList.isVisible = true
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    gifsAdapter.submitData(gifsListData)
                }
            }
        }
    }

//    private fun handleErrorState(pair: Pair<String, String>) {
//        with(binding) {
//            progressBar.isVisible = false
//            noData.isVisible = false
//            gifsList.isVisible = false
//            Log.e("GIF_ERROR", pair.second)
//            showDialogWithButton(
//                title = "Error",
//                message = pair.first,
//                buttonTitle = "Try again"
//            ) {
//                viewModel.getGifsList()
//            }
//        }
//    }

    private fun navigateDetails(gif: Gif) {
        findNavController().navigate(
            GifsListFragmentDirections.actionGifsListFragmentToGifDetailedFragment(
                gif
            )
        )
    }
}
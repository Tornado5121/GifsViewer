package com.zhadko.gifyviewer.features.gifsList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.zhadko.gifyviewer.base.BaseFragment
import com.zhadko.gifyviewer.databinding.FragmentGifsListBinding
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.extensions.collectOnLifeCycle
import com.zhadko.gifyviewer.extensions.showDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GifsListFragment : BaseFragment<FragmentGifsListBinding>(FragmentGifsListBinding::inflate) {

    private val viewModel by viewModel<GifsListViewModel>()
    private val gifsAdapter by inject<GifsAdapter> {
        parametersOf(
            ::navigateDetails
        )
    }

//    private val layoutManager: LinearLayoutManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        with(binding) {
            gifsList.adapter = gifsAdapter
            val manager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            gifsList.layoutManager = manager
            setPagination(manager)
        }
    }

    private fun setupObservers() {
        collectOnLifeCycle(viewModel.state, ::handleStateScreen)
    }

    private fun handleStateScreen(state: GifListStates) {
        when (state) {
            GifListStates.EmptyGifsList -> handleEmptyGifsList()
            is GifListStates.Error -> handleErrorState(state.pair)
            is GifListStates.GifsList -> handleSuccess(state.result)
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

    private fun handleSuccess(gifsListData: List<Gif>) {
        with(binding) {
            progressBar.isVisible = false
            noData.isVisible = false
            gifsList.isVisible = true
            gifsAdapter.submitList(gifsListData)
        }
    }

    private fun handleErrorState(pair: Pair<String, String>) {
        with(binding) {
            progressBar.isVisible = false
            noData.isVisible = false
            gifsList.isVisible = false
            Log.e("GIF_ERROR", pair.second)
            showDialog(
                title = "Error",
                message = pair.first,
                buttonTitle = "Try again"
            ) {
                viewModel.getGifsList()
            }
        }
    }

    private fun setPagination(manager: LinearLayoutManager) {
        binding.gifsList.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val currentItems = manager.childCount
                val totalItems = manager.itemCount
                val scrollOutItems = manager.findFirstVisibleItemPosition()
                if (currentItems + scrollOutItems == totalItems) {
                    viewModel.getGifsList()
                }
            }
        })
    }

    private fun navigateDetails(gif: Gif) {
        findNavController().navigate(
            GifsListFragmentDirections.actionGifsListFragmentToGifDetailedFragment(
                gif
            )
        )
    }
}
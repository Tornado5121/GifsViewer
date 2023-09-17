package com.zhadko.gifyviewer.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Fragment.collectOnLifeCycle(
    flow: Flow<T>,
    collect: FlowCollector<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(state) {
            flow.collect(collect)
        }
    }
}
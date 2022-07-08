package com.rai.irregularverbs.ui.listCardVerbs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rai.irregularverbs.adapters.ListVerbAdapter
import com.rai.irregularverbs.constants.MenuType.LIST
import com.rai.irregularverbs.databinding.FragmentListVerbBinding
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListVerbFragment : Fragment() {
    private var _binding: FragmentListVerbBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val viewModel by viewModel<ListVerbViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListVerbBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val listVerbAdapter = ListVerbAdapter(LIST)
            recyclerView.adapter = listVerbAdapter
            lifecycle.coroutineScope.launch {
                viewModel.fullAll().collect {
                    listVerbAdapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
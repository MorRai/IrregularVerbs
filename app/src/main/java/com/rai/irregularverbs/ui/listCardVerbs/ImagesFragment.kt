package com.rai.irregularverbs.ui.listCardVerbs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rai.irregularverbs.adapters.ListVerbAdapter
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentImagesBinding
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImagesFragment : Fragment() {
    private var _binding: FragmentImagesBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private var chapter: Int = 0

    private val viewModel by viewModel<ListVerbViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chapter = ImagesFragmentArgs.fromBundle(requireArguments()).chapter
        return FragmentImagesBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val listVerbAdapter = ListVerbAdapter(IMAGE)
            recyclerView.adapter = listVerbAdapter
            lifecycle.coroutineScope.launch {
                viewModel.fullPart(chapter).collect {
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
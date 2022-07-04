package com.rai.irregularverbs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rai.irregularverbs.IrregularVerbsApplication
import com.rai.irregularverbs.adapters.ListVerbAdapter
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentFlashcardBinding
import com.rai.irregularverbs.databinding.FragmentImagesBinding
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import com.rai.irregularverbs.viewmodels.ListVerbViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch




class ImagesFragment : Fragment() {
    private var _binding: FragmentImagesBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private var chapter: Int = 0

    private val viewModel: ListVerbViewModel by activityViewModels {
        ListVerbViewModelFactory(
            (activity?.application as IrregularVerbsApplication).database.irregularVerbsDao()
        )
    }


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
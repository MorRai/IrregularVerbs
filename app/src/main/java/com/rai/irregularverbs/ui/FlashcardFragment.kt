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
import com.rai.irregularverbs.adapters.FlashcardAdapter
import com.rai.irregularverbs.databinding.FragmentFlashcardBinding
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import com.rai.irregularverbs.viewmodels.ListVerbViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class FlashcardFragment : Fragment() {
    private var _binding: FragmentFlashcardBinding? = null
    private val binding get() = _binding!!

    private var chapter: Int = 0

    private lateinit var recyclerView: RecyclerView

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
        // Inflate the layout for this fragment
        _binding = FragmentFlashcardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val flashcardAdapter = FlashcardAdapter()
        recyclerView.adapter = flashcardAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullPart(chapter).collect {
                flashcardAdapter.submitList(it)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
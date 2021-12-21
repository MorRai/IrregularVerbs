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
import com.rai.irregularverbs.constants.Charpter.Most50
import com.rai.irregularverbs.constants.MenuType.LIST
import com.rai.irregularverbs.databinding.FragmentListVerbBinding
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import com.rai.irregularverbs.viewmodels.ListVerbViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


class ListVerbFragment : Fragment() {
    private var _binding: FragmentListVerbBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val viewModel: ListVerbViewModel by activityViewModels {
        ListVerbViewModelFactory(
            (activity?.application as IrregularVerbsApplication).database.irregularVerbsDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListVerbBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val listVerbAdapter = ListVerbAdapter(LIST)
        recyclerView.adapter = listVerbAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullAll().collect() {
                listVerbAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
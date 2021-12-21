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
import com.rai.irregularverbs.databinding.FragmentImagesBinding
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import com.rai.irregularverbs.viewmodels.ListVerbViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [ImagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagesFragment : Fragment() {
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    private var charpter: Int = 0

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
        charpter = ImagesFragmentArgs.fromBundle(requireArguments()).chapter
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val listVerbAdapter = ListVerbAdapter(IMAGE)
        recyclerView.adapter = listVerbAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullPart(charpter).collect() {
                listVerbAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.rai.irregularverbs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.rai.irregularverbs.IrregularVerbsApplication
import com.rai.irregularverbs.constants.Charpter.Most50
import com.rai.irregularverbs.constants.Charpter.Plus50
import com.rai.irregularverbs.constants.Charpter.Pro
import com.rai.irregularverbs.constants.MenuType.EXAM
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentCommonMenuBinding
import com.rai.irregularverbs.viewmodels.ExamViewModel
import com.rai.irregularverbs.viewmodels.ExamViewModelFactory
import com.rai.irregularverbs.viewmodels.ListVerbViewModel
import com.rai.irregularverbs.viewmodels.ListVerbViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CommonMenuFragment : Fragment() {

    private var _binding: FragmentCommonMenuBinding? = null
    private val binding get() = _binding!!

    private var typeMenu: Int = 0

    private val viewModel: ExamViewModel by activityViewModels {
        ExamViewModelFactory(
            (activity?.application as IrregularVerbsApplication).database.irregularVerbsDao()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  arguments?.let {
            typeMenu = CommonMenuFragmentArgs.fromBundle(requireArguments()).typeMenu

        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCommonMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (typeMenu == EXAM) {
        lifecycle.coroutineScope.launch {
            viewModel.getAvailability(Most50).collect {
                binding.most50Button.isEnabled = it != 0
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.getAvailability(Plus50).collect {
                binding.plus50Button.isEnabled = it != 0
            }
        }
        lifecycle.coroutineScope.launch {
            viewModel.getAvailability(Pro).collect {
                binding.proButton.isEnabled = it != 0
            }

        }

        }

        bind()
    }


    private fun returnAction(chapter:Int) : NavDirections{
        return when (typeMenu) {
            EXAM -> {
                viewModel.part = chapter
                viewModel.clearIrregular()
                CommonMenuFragmentDirections.actionCommonMenuFragmentToExamFragment(chapter)
            }
            IMAGE -> {
                CommonMenuFragmentDirections.actionCommonMenuFragmentToImagesFragment(chapter)
            }
            else -> {
                CommonMenuFragmentDirections.actionCommonMenuFragmentToFlashcardFragment(chapter)}
        }

    }



    private fun bind() {

        binding.apply {
            most50Button.setOnClickListener {
                viewModel.part = Most50
                val action = returnAction(Most50)
                findNavController().navigate(action)
            }
            plus50Button.setOnClickListener {
                viewModel.part = Most50
                val action = returnAction(Plus50)
                findNavController().navigate(action)
            }
            proButton.setOnClickListener {
                val action = returnAction(Pro)
                findNavController().navigate(action)
            }

        }
    }

}
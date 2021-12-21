package com.rai.irregularverbs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.rai.irregularverbs.constants.Charpter.Most50
import com.rai.irregularverbs.constants.Charpter.Plus50
import com.rai.irregularverbs.constants.Charpter.Pro
import com.rai.irregularverbs.constants.MenuType.EXAM
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentCommonMenuBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CommonMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommonMenuFragment : Fragment() {

    private var _binding: FragmentCommonMenuBinding? = null
    private val binding get() = _binding!!

    private var typeMenu: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  arguments?.let {
            typeMenu = CommonMenuFragmentArgs.fromBundle(requireArguments()).typeMenu

        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommonMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }


    private fun returnAction(chapter:Int) : NavDirections{
        return when (typeMenu) {
            EXAM -> CommonMenuFragmentDirections.actionCommonMenuFragmentToExamFragment(chapter)
            IMAGE -> CommonMenuFragmentDirections.actionCommonMenuFragmentToImagesFragment(chapter)
            else -> CommonMenuFragmentDirections.actionCommonMenuFragmentToFlashcardFragment(chapter)
        }

    }

    private fun bind() {

        binding.apply {
            most50Button.setOnClickListener {
                val action = returnAction(Most50)
                findNavController().navigate(action)
            }
            plus50Button.setOnClickListener {
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
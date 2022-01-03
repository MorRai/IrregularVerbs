package com.rai.irregularverbs.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rai.irregularverbs.constants.MenuType.EXAM
import com.rai.irregularverbs.constants.MenuType.FLASHCARD
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentVerbsMainBinding


class VerbsMainFragment : Fragment() {
    private var _binding: FragmentVerbsMainBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVerbsMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }



    private fun bind() {
        binding.apply {
            examButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToCommonMenuFragment(EXAM)
                findNavController().navigate(action)
            }
            listButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToListVerbFragment()
                findNavController().navigate(action)
            }
            imageButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToCommonMenuFragment(IMAGE)
                findNavController().navigate(action)
            }
            flascardButton.setOnClickListener {
                val action = VerbsMainFragmentDirections.actionVerbsMainFragmentToCommonMenuFragment(FLASHCARD)
                findNavController().navigate(action)
            }
        }
    }


}
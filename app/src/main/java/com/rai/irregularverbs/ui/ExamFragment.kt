package com.rai.irregularverbs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rai.irregularverbs.databinding.FragmentCommonMenuBinding
import com.rai.irregularverbs.databinding.FragmentExamBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ExamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExamFragment : Fragment() {
    private var _binding: FragmentExamBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExamBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}
package com.rai.irregularverbs.ui.commonMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.rai.irregularverbs.R
import com.rai.irregularverbs.constants.Charpter.Most50
import com.rai.irregularverbs.constants.Charpter.Plus50
import com.rai.irregularverbs.constants.Charpter.Pro
import com.rai.irregularverbs.constants.MenuType.EXAM
import com.rai.irregularverbs.constants.MenuType.IMAGE
import com.rai.irregularverbs.databinding.FragmentCommonMenuBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CommonMenuFragment : Fragment() {

    private var _binding: FragmentCommonMenuBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }
    private var typeMenu: Int = 0

    private var blockMost50 = false
    private var blockPlus50 = false
    private var blockPro = false


    private val viewModel by sharedViewModel<CommonMenuViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCommonMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAvailability()
        typeMenu = CommonMenuFragmentArgs.fromBundle(requireArguments()).typeMenu
        if (typeMenu == EXAM) {
            val mImage = ResourcesCompat.getDrawable(resources, R.drawable.completed, null)
                viewModel.blockMost50.onEach {
                    if (it) {
                        binding.most50Button.setCompoundDrawablesWithIntrinsicBounds(mImage,
                            null,
                            null,
                            null)
                    } else {
                        binding.most50Button.setCompoundDrawablesWithIntrinsicBounds(null,
                            null,
                            null,
                            null)
                    }
                    blockMost50 = it
                    viewModel.getAvailability()
                }.launchIn(viewLifecycleOwner.lifecycleScope)
                viewModel.blockPlus50.onEach {
                    if (it) {
                        binding.plus50Button.setCompoundDrawablesWithIntrinsicBounds(mImage,
                            null,
                            null,
                            null)
                    } else {
                        binding.plus50Button.setCompoundDrawablesWithIntrinsicBounds(null,
                            null,
                            null,
                            null)
                    }
                    blockPlus50 = it
                }.launchIn(viewLifecycleOwner.lifecycleScope)
                viewModel.blockPro.onEach {
                    if (it) {
                        binding.proButton.setCompoundDrawablesWithIntrinsicBounds(mImage,
                            null,
                            null,
                            null)
                    } else {
                        binding.proButton.setCompoundDrawablesWithIntrinsicBounds(null,
                            null,
                            null,
                            null)
                    }
                    blockPro = it
                }.launchIn(viewLifecycleOwner.lifecycleScope)

        }
        bind()

    }


    private fun returnAction(chapter: Int): NavDirections? {
        when (typeMenu) {
            EXAM -> {
                viewModel.part = chapter
                return if ((blockMost50 && chapter == Most50) || (blockPlus50 && chapter == Plus50) || (blockPro && chapter == Pro)) {
                    val myDialogFragment = DumpingDialog()
                    val manager = (activity as FragmentActivity).supportFragmentManager
                    myDialogFragment.show(manager, "myDialog")
                    null
                } else {
                    CommonMenuFragmentDirections.actionCommonMenuFragmentToExamFragment(
                        chapter)
                }
            }
            IMAGE -> {
                return CommonMenuFragmentDirections.actionCommonMenuFragmentToImagesFragment(chapter)
            }
            else -> {
                return CommonMenuFragmentDirections.actionCommonMenuFragmentToFlashcardFragment(
                    chapter)
            }
        }

    }


    private fun bind() {
       with(binding){
            most50Button.setOnClickListener {
                val action = returnAction(Most50)
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
            plus50Button.setOnClickListener {
                val action = returnAction(Plus50)
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
            proButton.setOnClickListener {
                val action = returnAction(Pro)
                if (action != null) {
                    findNavController().navigate(action)
                }
            }

        }
    }

}
package com.rai.irregularverbs.ui.exam


import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.rai.irregularverbs.R
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.databinding.FragmentExamBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*


class ExamFragment : Fragment() {
    private var _binding: FragmentExamBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val args by navArgs<ExamFragmentArgs>()

    private val viewModel by viewModel<ExamViewModel>{
        parametersOf(args.chapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExamBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomVerb.onEach {
                if (it != null) {
                    bind(it)
                } else {
                   // findNavController().popBackStack()
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.previousVerb.onEach {
                if (it != null) {
                    refresh(it)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.checkVisibility.onEach {
                refreshVisibility(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.progress.onEach {
                val fileSize = 300// нужно получать из базы
                binding.progressBar.max = fileSize
                binding.progressBar.progress = it
                val percentage = (it.toDouble() / fileSize * 100)
                binding.progress.text = "${String.format("%.2f", percentage)}%"
                binding.level.text = getString(R.string.level, args.chapter.toString())
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun bind(irregularVerbs: IrregularVerbs) {
        with(binding) {
            val textCheck: String
            form1.text = irregularVerbs.form1
            val country = Locale.getDefault().country
            if (country == "RU") {
                translate.text = irregularVerbs.ru
            }
            val getV2orV3 = viewModel.getV2orV3(irregularVerbs)
            if (getV2orV3 == 2) {
                editText.hint = context?.getString(R.string.v2_text)
                typeForm.text = context?.getString(R.string.v2)
                textCheck = irregularVerbs.form2
            } else {
                editText.hint = context?.getString(R.string.v3_text)
                typeForm.text = context?.getString(R.string.v3)
                textCheck = irregularVerbs.form3
            }
            buttonOk.setOnClickListener {
                //hide keyboard
                val imm: InputMethodManager =
                    context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)

                viewModel.updateEditText(editText.text.toString())
                viewModel.nextVerb(irregularVerbs, textCheck, editText.text.toString(), getV2orV3)
                editText.setText("")
                if (getV2orV3 == 2) {
                    mcvForm2.setBackgroundColor(Color.GREEN)
                    mcvForm3.setBackgroundColor(resources.getColor(R.color.primaryDarkColor))
                } else {
                    mcvForm2.setBackgroundColor(resources.getColor(R.color.primaryDarkColor))
                    mcvForm3.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }

    private fun refresh(irregularVerbs: IrregularVerbs) {
        with(binding) {
            answerText.text = getString(R.string.text_answer, viewModel.editText)
            answerText.paintFlags = answerText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            answerForm1.text = irregularVerbs.form1
            answerForm2.text = irregularVerbs.form2
            answerForm3.text = irregularVerbs.form3
        }
    }

    private fun refreshVisibility(checkVisibility: Boolean) {
        with(binding) {
            if (checkVisibility) {
                answerText.visibility = View.VISIBLE
                textError.visibility = View.VISIBLE
                mcvForm1.visibility = View.VISIBLE
                mcvForm2.visibility = View.VISIBLE
                mcvForm3.visibility = View.VISIBLE
            } else {
                answerText.visibility = View.INVISIBLE
                textError.visibility = View.INVISIBLE
                mcvForm1.visibility = View.INVISIBLE
                mcvForm2.visibility = View.INVISIBLE
                mcvForm3.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
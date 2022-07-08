package com.rai.irregularverbs.ui


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import com.rai.irregularverbs.IrregularVerbsApplication
import com.rai.irregularverbs.R
import com.rai.irregularverbs.data.IrregularVerbs
import com.rai.irregularverbs.databinding.FragmentExamBinding
import com.rai.irregularverbs.viewmodels.ExamViewModel
import android.app.Activity
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ExamFragment : Fragment() {
    private var _binding: FragmentExamBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }


    private val viewModel by sharedViewModel<ExamViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExamBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomVerb.observe(viewLifecycleOwner) {
            if (it != null) {
                bind(it)
            } else {
                findNavController().popBackStack()
            }

        }
        viewModel.previousVerb.observe(viewLifecycleOwner) {
            if (it != null) {
                refresh(it)
            }
        }
        viewModel.checkVisibility.observe(viewLifecycleOwner) {
            refreshVisibility(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            val fileSize = 300
            binding.progressBar.max = fileSize
            binding.progressBar.progress = it
            val percentage = (it.toDouble() / fileSize * 100)
            binding.progress.text = "${String.format("%.2f", percentage)}%"
            binding.level.text = getString(R.string.level, viewModel.part.toString())
        }
    }



    private fun bind(irregularVerbs: IrregularVerbs) {
        binding.apply {
            val textCheck:String
            form1.text = irregularVerbs.form1
            val country = Locale.getDefault().country
            if (country=="RU") {
            translate.text = irregularVerbs.ru}
            val getV2orV3 = viewModel.getV2orV3(irregularVerbs)
            if (getV2orV3 == 2){
                editText.hint= context?.getString(R.string.v2_text)
                typeForm.text = context?.getString(R.string.v2)
                textCheck = irregularVerbs.form2
            }else{
                editText.hint= context?.getString(R.string.v3_text)
                typeForm.text = context?.getString(R.string.v3)
                textCheck = irregularVerbs.form3
            }
            buttonOk.setOnClickListener {
                //hide keyboard
                val imm: InputMethodManager =
                    context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)

                viewModel.updateEditText(editText.text.toString())
                viewModel.nextVerb(irregularVerbs,textCheck,editText.text.toString(),getV2orV3)
                editText.setText("")
                if (getV2orV3 == 2){
                    mcvForm2.setBackgroundColor(Color.GREEN)
                    mcvForm3.setBackgroundColor(resources.getColor(R.color.primaryDarkColor))}
                else {
                    mcvForm2.setBackgroundColor(resources.getColor(R.color.primaryDarkColor))
                    mcvForm3.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }


    private fun refresh(irregularVerbs: IrregularVerbs){
        binding.answerText.text =getString(R.string.text_answer,viewModel.editText)
        binding.answerText.paintFlags =  binding.answerText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.answerForm1.text = irregularVerbs.form1
        binding.answerForm2.text = irregularVerbs.form2
        binding.answerForm3.text = irregularVerbs.form3
    }

    private fun refreshVisibility(checkVisibility: Boolean) {
        if (checkVisibility) {
            binding.answerText.visibility = View.VISIBLE
            binding.textError.visibility = View.VISIBLE
            binding.mcvForm1.visibility = View.VISIBLE
            binding.mcvForm2.visibility = View.VISIBLE
            binding.mcvForm3.visibility = View.VISIBLE
        } else {
            binding.answerText.visibility = View.INVISIBLE
            binding.textError.visibility = View.INVISIBLE
            binding.mcvForm1.visibility = View.INVISIBLE
            binding.mcvForm2.visibility = View.INVISIBLE
            binding.mcvForm3.visibility = View.INVISIBLE

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
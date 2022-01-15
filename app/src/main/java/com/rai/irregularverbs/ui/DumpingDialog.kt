package com.rai.irregularverbs.ui

import android.app.AlertDialog
import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.rai.irregularverbs.IrregularVerbsApplication
import com.rai.irregularverbs.R
import com.rai.irregularverbs.viewmodels.ExamViewModel
import com.rai.irregularverbs.viewmodels.ExamViewModelFactory


class DumpingDialog: DialogFragment() {

    private val viewModel: ExamViewModel by activityViewModels {
        ExamViewModelFactory(
            (activity?.application as IrregularVerbsApplication).database.irregularVerbsDao()
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity,R.style.CustomDialogTheme)
        return builder
            .setTitle(R.string.app_name)
            .setMessage(R.string.text_dialog)
            .setPositiveButton(R.string.ok) { dialog, id ->
                viewModel.dumpPart()
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}
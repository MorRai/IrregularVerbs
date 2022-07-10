package com.rai.irregularverbs.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rai.irregularverbs.R
import com.rai.irregularverbs.viewmodels.ExamViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DumpingDialog: DialogFragment() {

    private val viewModel by sharedViewModel<ExamViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity,R.style.CustomDialogTheme)
        return builder
            .setTitle(R.string.app_name)
            .setMessage(R.string.text_dialog)
            .setPositiveButton(R.string.ok) { _, _ ->
                viewModel.dumpPart()
                viewModel.getAvailability()
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}
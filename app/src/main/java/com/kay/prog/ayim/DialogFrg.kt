package com.kay.prog.ayim

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogFrg : DialogFragment() {
    private lateinit var listener: OnItemClicked

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listener = context as OnItemClicked

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Удалить итем?")
                .setPositiveButton("Да") { dialog, id ->
                    listener.yesClicked()
                }
                .setNegativeButton("Нет") { dialog, id ->
                        dialog.cancel()
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
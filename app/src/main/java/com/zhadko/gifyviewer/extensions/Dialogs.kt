package com.zhadko.gifyviewer.extensions

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showDialogWithButton(
    title: String,
    message: String = "",
    buttonTitle: String,
    action: () -> Unit
) {
    AlertDialog.Builder(requireContext()).setTitle(title).setMessage(message)
        .setPositiveButton(buttonTitle) { dialog, _ ->
            action()
            dialog.dismiss()
        }.create().show()
}
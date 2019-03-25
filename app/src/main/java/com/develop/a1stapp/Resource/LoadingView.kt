package com.develop.a1stapp.Resource

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.develop.a1stapp.R
import kotlinx.android.synthetic.main.loading_view.view.*

class LoadingView(private val activity: Activity) {
    var dialog: Dialog? = null

    fun show() {
        dialog = Dialog(activity)
        val dialog = dialog!!

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.loading_view)
        dialog.show()
    }

    fun close() {
        val dialog = dialog!!
        dialog.dismiss()
    }
}
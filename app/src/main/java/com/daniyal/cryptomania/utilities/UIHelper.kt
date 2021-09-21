package com.daniyal.cryptomania.utilities

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.daniyal.cryptomania.R

object UIHelper {

    fun showDialog(context: Context?, msg: String?) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.layout_dialog)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tv_done = dialog.findViewById<LinearLayout>(R.id.layout_done)
        val tv_msg = dialog.findViewById<TextView>(R.id.tv_msg)
        dialog.setCancelable(false)
        tv_msg.text = msg
        tv_done.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
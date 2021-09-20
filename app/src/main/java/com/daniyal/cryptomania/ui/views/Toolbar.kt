package com.daniyal.cryptomania.ui.views

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.daniyal.cryptomania.R
import kotlinx.android.synthetic.main.toolbar.view.*

class Toolbar(context: Context?) : RelativeLayout(context) {


    init {
        initLayout(context!!)
    }

    fun Toolbar(context: Context, attrs: AttributeSet?) {
        initLayout(context)
        attrs?.let { initAttrs(context, it) }
    }

    fun Toolbar(context: Context, attrs: AttributeSet?, defStyle: Int) {
        initLayout(context)
        attrs?.let { initAttrs(context, it) }
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {}


    fun getTxtTitle(): TextView {
        return txtTitle
    }

    fun getContTitlebar(): LinearLayout {
        return contTitlebar
    }

    fun getToolbar_(): Toolbar? {
        return toolbar
    }

    private fun initLayout(context: Context) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.toolbar, this)
    }


    fun setSubHeading(subHeading: String?) {
        getTxtTitle().visibility = VISIBLE
        //getTxtTitle().setText(subHeading + "");
        getTxtTitle().text = Html.fromHtml(subHeading)
    }


    fun setTitleBarColor(bgColor: Int) {
        getContTitlebar().setBackgroundColor(resources.getColor(bgColor))
    }
}
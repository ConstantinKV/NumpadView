/**
 * @author Fentury Team
 * Copyright (c) 2018 Salt Edge. All rights reserved.
 */
package com.fentury.common.pinpadview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_pinpad.view.*

class PinpadView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs),
        View.OnClickListener {

    var clickListener: PinpadKeyClickListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_pinpad, this)
        for (i in 0..pinpadLayout.childCount) pinpadLayout.getChildAt(i)?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (!isEnabled) return
        val viewId = view?.id ?: return
        when (viewId) {
            R.id.fingerView -> clickListener?.onKeyClick(PinpadView.Control.FINGER)
            R.id.deleteView -> clickListener?.onKeyClick(PinpadView.Control.DELETE)
            else -> {
                val text: String = (view as? NumpadKeyView)?.text ?: return
                clickListener?.onKeyClick(Control.NUMBER, text)
            }
        }
    }

    fun setupFingerAction(active: Boolean) {
        fingerView?.visibility = if (active) View.VISIBLE else View.GONE
    }

    interface PinpadKeyClickListener {
        fun onKeyClick(type: Control, value: String = "")
    }

    enum class Control {
        DELETE, FINGER, NUMBER
    }
}

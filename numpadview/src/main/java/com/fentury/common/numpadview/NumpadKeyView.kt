/**
 * @author Fentury Team
 * Copyright (c) 2018 Salt Edge. All rights reserved.
 */
package com.fentury.common.numpadview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_numpad_key.view.*

class NumpadKeyView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var text: String = ""
        private set

    init {
        LayoutInflater.from(context).inflate(R.layout.view_numpad_key, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.NumpadKeyView)
        try {
            val text: String = attributes.getString(R.styleable.NumpadKeyView_android_text) ?: ""
            val textSize = attributes.getDimension(R.styleable.NumpadKeyView_android_textSize, 24f)
            val showBottomDivider = attributes.getBoolean(R.styleable.NumpadKeyView_showBottomDivider, false)
            setupViews(text, textSize, showBottomDivider)
        } finally {
            attributes.recycle()
        }
    }

    @SuppressLint("GetContentDescriptionOverride")
    override fun getContentDescription(): CharSequence = text

    private fun setupViews(text: String, textSize: Float, showBottomDivider: Boolean) {
        this.text = text
        keyLabelView?.text = text
        keyLabelView?.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        bottomKeyDividerView?.visibility = if (showBottomDivider) View.VISIBLE else View.GONE
    }
}

package com.example.wdoverlay

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

/** Black-and-white look shared by the main screen and the overlay. */
object Mono {
    val BLACK: Int = Color.BLACK
    val WHITE: Int = Color.WHITE
    val GRAY: Int = Color.parseColor("#B0B0B0")
    private val PANEL: Int = Color.parseColor("#F2000000")
    private val BUTTON_FILL: Int = Color.parseColor("#111111")

    private fun shape(fill: Int, stroke: Int, radius: Float, strokePx: Int) = GradientDrawable().apply {
        setColor(fill)
        cornerRadius = radius
        setStroke(strokePx, stroke)
    }

    private fun strokeWidth(density: Float): Int = (1.5f * density).toInt().coerceAtLeast(1)

    fun button(b: Button, density: Float) {
        val radius = 8f * density
        val s = strokeWidth(density)
        b.background = StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_pressed), shape(WHITE, WHITE, radius, s))
            addState(intArrayOf(), shape(BUTTON_FILL, WHITE, radius, s))
        }
        b.setTextColor(
            ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
                intArrayOf(BLACK, WHITE)
            )
        )
        b.isAllCaps = false
        val gap = (3 * density).toInt()
        b.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(0, gap, 0, gap) }
    }

    fun edit(e: EditText) {
        e.setTextColor(WHITE)
        e.setHintTextColor(GRAY)
        e.backgroundTintList = ColorStateList.valueOf(WHITE)
    }

    fun panelBackground(v: View, density: Float) {
        v.background = shape(PANEL, WHITE, 10f * density, strokeWidth(density))
    }
}

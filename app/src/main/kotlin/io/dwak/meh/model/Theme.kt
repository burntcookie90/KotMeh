package io.dwak.meh.model

import android.graphics.Color

class Theme(
        private val accentColor : String,
        val foreground : String,
        private val backgroundColor : String,
        val backgroundImage : String) {

    fun getParsedAccentColor() : Int {
        return Color.parseColor(accentColor)
    }

    fun getParsedBackgroundColor() : Int {
        return Color.parseColor(backgroundColor)
    }

}
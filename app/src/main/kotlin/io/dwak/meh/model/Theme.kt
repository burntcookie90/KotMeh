package io.dwak.meh.model

import android.graphics.Color

class Theme(
        private val accentColor : String,
        val foreground : String,
        private val backgroundColor : String,
        val backgroundImage : String) {

    public companion object {
        public val FOREGROUND_DARK : String = "dark"
        public val FOREGROUND_LIGHT : String = "light"
    }

    val parsedAccentColor : Int = Color.parseColor(accentColor)

    val parsedBackgroundColor : Int = Color.parseColor(backgroundColor)

}
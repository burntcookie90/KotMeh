package io.dwak.meh.model

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable

class Theme(
        private val accentColor : String,
        val foreground : String,
        private val backgroundColor : String,
        val backgroundImage : String) {

    public companion object {
        public val FOREGROUND_DARK : String = "dark"
        public val FOREGROUND_LIGHT : String = "light"
    }

    fun getPressedAccentColorDrawable() : ColorDrawable {
        val accentColor = Color.parseColor(accentColor)
        val accentColorDrawable = ColorDrawable(accentColor)
        val filter = PorterDuffColorFilter(accentColor, PorterDuff.Mode.MULTIPLY)
        accentColorDrawable.setColorFilter(filter)
        return accentColorDrawable
    }

    fun getParsedAccentColor() : Int {
        return Color.parseColor(accentColor)
    }

    fun getParsedBackgroundColor() : Int {
        return Color.parseColor(backgroundColor)
    }

}
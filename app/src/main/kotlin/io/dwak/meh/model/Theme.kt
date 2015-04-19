package io.dwak.meh.model

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import rx.Observable

class Theme(
        val accentColor : String,
        val foreground : String,
        val backgroundColor : String,
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

    fun getParsedAccentColor() : Int = Color.parseColor(expandColor(accentColor))

    fun getParsedBackgroundColor() : Int = Color.parseColor(expandColor(backgroundColor))

    fun expandColor(colorString : String) : String {
        return when (colorString.length()) {
            4 -> {
                "${colorString[0]}${colorString[1]}${colorString[1]}${colorString[2]}${colorString[2]}${colorString[3]}${colorString[3]}"
            }
            else    -> {
                colorString
            }
        }
    }
}
package io.dwak.meh.model

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import kotlin.test.assertEquals

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
        if(colorString.length() <= 4){
            var color = colorString.substring(1, colorString.length())
            var newString = "";
            for (i in color) {
                newString+=i;
                newString+=i;
            }
            return "#${newString}"
        }
        else {
            return colorString
        }
    }
}
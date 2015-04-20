package io.dwak.meh.model

import android.text.Html
import android.text.Spanned
import com.commonsware.cwac.anddown.AndDown
import io.dwak.meh.markdownToSpanned

class Story(
        val title : String,
        val body : String) {

    fun getFormattedBody() : Spanned = AndDown().markdownToSpanned(body)

}
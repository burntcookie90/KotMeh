package io.dwak.meh.model

import android.text.Html
import android.text.Spanned
import com.commonsware.cwac.anddown.AndDown

class Story(
        val title : String,
        val body : String) {

    fun getFormattedBody() : Spanned = body.toSpannedFromMarkdown()

}
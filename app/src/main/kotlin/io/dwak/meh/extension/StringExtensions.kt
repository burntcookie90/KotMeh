package io.dwak.meh.extension

import android.text.Html
import android.text.Spanned
import com.commonsware.cwac.anddown.AndDown

fun String.toSpannedFromMarkdown() : Spanned {
    return Html.fromHtml(AndDown().markdownToHtml(this))
}

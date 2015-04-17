package io.dwak.meh

import android.graphics.Color
import android.text.Html
import android.text.Spanned
import com.commonsware.cwac.anddown.AndDown

fun String.toSpannedFromMarkdown() : Spanned = Html.fromHtml(AndDown().markdownToHtml(this))


package io.dwak.meh

import android.animation.LayoutTransition
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Html
import android.text.Spanned
import android.view.ViewManager
import android.widget.LinearLayout
import com.commonsware.cwac.anddown.AndDown
import kotlinx.android.anko.AnkoException
import kotlinx.android.anko._LinearLayout
import kotlinx.android.anko.__dslAddView

/**
 * {@link String}
 */
fun String.toSpannedFromMarkdown() : Spanned = Html.fromHtml(AndDown().markdownToHtml(this))


/**
 * {@link anko}
 **/

// {@link LinearLayout}
val horizontalLayoutFactory = { ctx : Context ->
    val v = kotlinx.android.anko._LinearLayout(ctx)
    v.setOrientation(LinearLayout.HORIZONTAL)
    v
}

public fun ViewManager.horizontalLayout(init : _LinearLayout.() -> Unit) : LinearLayout =
        __dslAddView(horizontalLayoutFactory, init, this): LinearLayout

public fun Activity.horizontalLayout(init : _LinearLayout.() -> Unit) : LinearLayout =
        __dslAddView(horizontalLayoutFactory, init, this): LinearLayout

public fun Context.horizontalLayout(init : _LinearLayout.() -> Unit) : LinearLayout =
        __dslAddView(horizontalLayoutFactory, init, this): LinearLayout

// {@link FrameLayout}
public var android.widget.FrameLayout.animateLayoutChanges : Boolean
    get() = throw AnkoException("'FrameLayout.animateLayoutChanges' property doesn't have a getter")
    set(b) = setLayoutTransition(LayoutTransition())


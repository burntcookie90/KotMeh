package io.dwak.meh

import android.animation.LayoutTransition
import android.app.Activity
import android.content.Context
import android.text.Html
import android.text.Spanned
import android.view.ViewManager
import android.widget.ImageView
import android.widget.LinearLayout
import com.commonsware.cwac.anddown.AndDown
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.anko.AnkoException
import kotlinx.android.anko._LinearLayout
import kotlinx.android.anko.__dslAddView

/**
 * {@link AndDown}
 */
fun AndDown.markdownToSpanned(v : String) : Spanned = Html.fromHtml(markdownToHtml(v))


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

/**
 * {@link Picasso}
 */
public fun Activity.loadImage(url : String, imageView : ImageView) : Unit = Picasso.with(this).load(url).into(imageView)

public fun Activity.loadImage(url : String, target : Target) : Unit = Picasso.with(this) .load(url) .into(target)

public fun Context.loadImage(url : String, imageView : ImageView) : Unit = Picasso.with(this).load(url).into(imageView)

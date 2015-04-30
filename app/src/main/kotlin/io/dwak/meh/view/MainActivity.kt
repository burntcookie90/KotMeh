package io.dwak.meh.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.dwak.meh.R
import io.dwak.meh.animateLayoutChanges
import io.dwak.meh.base.BaseActivity
import io.dwak.meh.horizontalLayout
import io.dwak.meh.loadImage
import io.dwak.meh.model.Meh
import io.dwak.meh.model.Theme
import io.dwak.meh.presenter.MainPresenterImpl
import kotlinx.android.anko.*
import kotlin.properties.Delegates

class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {
    override fun setView() {
        presenter.view = this
    }

    override val presenterClass : Class<MainPresenterImpl> = javaClass()

    private var shareButton : ImageButton by Delegates.notNull()
    private var backgroundImage : ImageView by Delegates.notNull()
    private var rootView : View by Delegates.notNull()
    private var container : View by Delegates.notNull()
    private var titleView : TextView by Delegates.notNull()
    private var storyView : TextView by Delegates.notNull()
    private var viewPager : ViewPager by Delegates.notNull()
    private var buyButton : Button by Delegates.notNull()
    private var specificationsView : TextView by Delegates.notNull()
    private val imagePagerAdapter = ImagePagerAdapter(this)
    private var featuresExpansionButton : LinearLayout by Delegates.notNull()
    private var featuresContainer : FrameLayout by Delegates.notNull()
    private var featuresTextView : TextView by Delegates.notNull()
    private var expansionArrowImage : ImageView by Delegates.notNull()

    override fun populatePage(currentMeh : Meh) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(currentMeh.deal.theme.getParsedAccentColor());
        }
        titleView.text = currentMeh.deal.title
        titleView.textColor = currentMeh.deal.theme.getParsedAccentColor()
        storyView.text = currentMeh.deal.story.getFormattedBody()
        when (currentMeh.deal.theme.foreground) {
            Theme.FOREGROUND_DARK  -> {
                storyView.textColor = getResources().getColor(android.R.color.black)
                expansionArrowImage.imageResource = R.drawable.ic_action_expand_more
                shareButton.imageResource = R.drawable.ic_action_share_black
            }
            Theme.FOREGROUND_LIGHT -> {
                storyView.textColor = getResources().getColor(android.R.color.white)
                expansionArrowImage.imageResource = R.drawable.ic_action_expand_more_white
                shareButton.imageResource = R.drawable.ic_action_share_white
            }
        }

        Picasso.with(this)
                .load(currentMeh.deal.theme.backgroundImage)
                .fit()
                .into(backgroundImage)

        imagePagerAdapter.imageUrls = currentMeh.deal.photos
        imagePagerAdapter.notifyDataSetChanged()

        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArray(-android.R.attr.state_enabled), currentMeh.deal.theme.getPressedAccentColorDrawable())
        stateListDrawable.addState(intArray(android.R.attr.state_enabled, android.R.attr.state_pressed), currentMeh.deal.theme.getPressedAccentColorDrawable())
        stateListDrawable.addState(intArray(), ColorDrawable(currentMeh.deal.theme.getParsedAccentColor()))

        val shareDrawable = StateListDrawable()
        shareDrawable.addState(intArray(-android.R.attr.state_enabled), currentMeh.deal.theme.getPressedAccentColorDrawable())
        shareDrawable.addState(intArray(android.R.attr.state_enabled, android.R.attr.state_pressed), currentMeh.deal.theme.getPressedAccentColorDrawable())
        shareDrawable.addState(intArray(), ColorDrawable(currentMeh.deal.theme.getParsedAccentColor()))


        if (!TextUtils.isEmpty(currentMeh.deal.soldOutAt)) {
            buyButton.enabled = false
            buyButton.text = "Sold out"
        }
        else {
            buyButton.enabled = true
            buyButton.text = "${currentMeh.deal.getFormattedPriceString()}\nBuy it"
            buyButton.onClick { browse(currentMeh.deal.url) }
        }

        buyButton.background = stateListDrawable
        buyButton.textColor = currentMeh.deal.theme.getParsedBackgroundColor()

        shareButton.background = shareDrawable
        shareButton.onClick { share("http://meh.com") }

        specificationsView.text = currentMeh.deal.getFormattedSpecifications()

        featuresExpansionButton.visibility = View.VISIBLE
        featuresTextView.text = currentMeh.deal.getFormattedFeatures()
    }


    override fun onCreate(savedInstanceState : Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        getLayout()

        storyView.movementMethod = LinkMovementMethod.getInstance()
        specificationsView.movementMethod = LinkMovementMethod.getInstance()
        viewPager.adapter = imagePagerAdapter

        presenter.getCurrentMeh()
    }



    private fun getLayout() {
        rootView = relativeLayout {
            backgroundImage = imageView().layoutParams(width = matchParent,
                                                       height = matchParent)
            container = scrollView {
                backgroundColor = android.R.color.transparent
                verticalLayout {
                    padding = dip(16)

                    viewPager = viewPager().layoutParams(width = matchParent,
                                                         height = dip(400))

                    horizontalLayout {
                        weightSum = 10.0f

                        buyButton = button {
                            text = "Loading..."
                        }.layoutParams(width = dip(0),
                                       height = matchParent){
                            weight = 8.5f
                            rightMargin = dip(2)
                        }

                        shareButton = imageButton().layoutParams(width = dip(0),
                                                                 height = matchParent) {
                            weight = 1.5f
                            leftMargin = dip(2)
                        }
                    }.layoutParams(width = matchParent,
                                   height = dip(52))

                    titleView = textView {
                        textSize = 24f
                    }.layoutParams(width = matchParent,
                                   height = wrapContent) {
                        topMargin = dip(8)
                        bottomMargin = dip(8)
                    }

                    storyView = textView()

                    specificationsView = textView()
                            .layoutParams(width = matchParent,
                                          height = wrapContent) {
                                topMargin = dip(8)
                            }

                    featuresExpansionButton = horizontalLayout {
                        weightSum = 10.0f
                        visibility = View.GONE
                        onClick({
                                    when (featuresContainer.visibility) {
                                        View.VISIBLE -> {
                                            featuresContainer.visibility = View.GONE
                                        }
                                        View.GONE    -> {
                                            featuresContainer.visibility = View.VISIBLE
                                        }
                                    }
                                })

                        textView {
                            textSize = 20f
                            text = "Features"
                        }.layoutParams(width = dip(0),
                                       height = matchParent) {
                            weight = 9.0f
                        }

                        expansionArrowImage = imageView().layoutParams(width = dip(0),
                                                                       height = matchParent) {
                            weight = 1.0f
                        }
                    }.layoutParams(width = matchParent,
                                   height = dip(48))

                    featuresContainer = frameLayout {
                        animateLayoutChanges = true
                        visibility = View.GONE
                        featuresTextView = textView()
                    }.layoutParams(width = matchParent,
                                   height = wrapContent)


                }
            }
        }
    }

}


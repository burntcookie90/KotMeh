package io.dwak.meh.view

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import android.widget.TextView
import io.dwak.meh.base.BaseActivity
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

    private var titleView : TextView by Delegates.notNull()
    private var storyView : TextView by Delegates.notNull()
    private var rootView : View by Delegates.notNull()
    private var viewPager : ViewPager by Delegates.notNull()
    private var buyButton : Button by Delegates.notNull()
    private var specificationsView : TextView by Delegates.notNull()
    private val imagePagerAdapter = ImagePagerAdapter(this)

    override fun populatePage(currentMeh : Meh) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(currentMeh.deal.theme.getParsedAccentColor());
        }
        titleView.text = currentMeh.deal.title
        titleView.textColor = currentMeh.deal.theme.getParsedAccentColor()
        storyView.text = currentMeh.deal.story.getFormattedBody()
        when (currentMeh.deal.theme.foreground) {
            Theme.FOREGROUND_DARK  -> {
                storyView.textColor = getResources().getColor(android.R.color.black)
            }
            Theme.FOREGROUND_LIGHT -> {
                storyView.textColor = getResources().getColor(android.R.color.white)
            }
        }
        rootView.backgroundColor = currentMeh.deal.theme.getParsedBackgroundColor()
        imagePagerAdapter.imageUrls = currentMeh.deal.photos
        imagePagerAdapter.notifyDataSetChanged()

        val stateListDrawable : StateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArray(android.R.attr.state_pressed), currentMeh.deal.theme.getPressedAccentColorDrawable())
        stateListDrawable.addState(intArray(), ColorDrawable(currentMeh.deal.theme.getParsedAccentColor()))

        buyButton.text = "${currentMeh.deal.getFormattedPriceString()}\nBuy it"
        buyButton.background = stateListDrawable
        buyButton.textColor = currentMeh.deal.theme.getParsedBackgroundColor()
        buyButton.setOnClickListener { browse(currentMeh.deal.url) }

        specificationsView.text = currentMeh.deal.getFormattedSpecifications()
    }


    override fun onCreate(savedInstanceState : Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        getLayout()
        viewPager.adapter = imagePagerAdapter
        presenter.getCurrentMeh()
    }


    private fun getLayout() {
        rootView = scrollView {
            verticalLayout {
                padding = dip(16)

                viewPager = viewPager().layoutParams(width = matchParent, height = dip(400))

                buyButton = button().layoutParams(width = matchParent, height = wrapContent)

                titleView = textView {
                    textSize = 24f
                }.layoutParams(width = matchParent,
                               height = wrapContent) {
                    topMargin = dip(8)
                    bottomMargin = dip(8)
                }

                storyView = textView()

                textView {
                    textSize = 20f
                    text = "Specifications"
                }.layoutParams(width = matchParent,
                               height = wrapContent) {
                    topMargin = dip(8)
                }

                specificationsView = textView().layoutParams(width = matchParent,
                                                             height = wrapContent) {
                    topMargin = dip(8)
                }

            }
        }
    }

}
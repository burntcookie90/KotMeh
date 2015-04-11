package io.dwak.meh.view

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import android.widget.TextView
import io.dwak.meh.base.BaseActivity
import io.dwak.meh.model.Meh
import io.dwak.meh.presenter.MainPresenterImpl
import kotlinx.android.anko.*
import kotlin.properties.Delegates

class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {
    override fun setView() {
        presenter.view = this
    }

    override fun getPresenterClass() : Class<MainPresenterImpl> = javaClass()

    private var titleView : TextView by Delegates.notNull()
    private var storyView : TextView by Delegates.notNull()
    private var rootView : View by Delegates.notNull()
    private var viewPager : ViewPager by Delegates.notNull()
    private var buyButton : Button by Delegates.notNull()
    val imagePagerAdapter = ImagePagerAdapter(this)

    override fun populatePage(currentMeh : Meh) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(currentMeh.deal.theme.getParsedAccentColor())
        }
        titleView.text = currentMeh.deal.title
        titleView.textColor = currentMeh.deal.theme.getParsedAccentColor()
        storyView.text = currentMeh.deal.story.getFormattedBody()
        when(currentMeh.deal.theme.foreground) {
            "dark" -> {
                storyView.textColor = getResources().getColor(android.R.color.black)
            }
            "light" -> {
                storyView.textColor = getResources().getColor(android.R.color.white)
            }
        }
        rootView.backgroundColor = currentMeh.deal.theme.getParsedBackgroundColor()
        imagePagerAdapter.imageUrls = currentMeh.deal.photos
        imagePagerAdapter.notifyDataSetChanged()
        buyButton.text = "${currentMeh.deal.formattedPriceString}\nBuy it"
        buyButton.backgroundColor = currentMeh.deal.theme.getParsedAccentColor()
        buyButton.textColor = currentMeh.deal.theme.getParsedBackgroundColor()
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

                viewPager = viewPager {

                }.layoutParams(width = matchParent, height = dip(400)) {}

                buyButton = button().layoutParams(width = matchParent, height = wrapContent)

                titleView = textView {
                    textSize = 24f
                }.layoutParams(width = matchParent, height = matchParent) {
                    bottomMargin = dip(8)
                }

                storyView = textView {

                }

            }
        }
    }

}
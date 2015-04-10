package io.dwak.meh.view

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewManager
import android.widget.TextView
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.DefaultSliderView
import io.dwak.meh.base.BaseActivity
import io.dwak.meh.model.Meh
import io.dwak.meh.presenter.MainPresenterImpl
import kotlinx.android.anko.*

class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {
    override fun setView() {
        presenter.view = this
    }

    override fun getPresenterClass() : Class<MainPresenterImpl> = javaClass()

    var titleView : TextView? = null
    var storyView : TextView? = null
    var rootView : View? = null
    var viewPager : ViewPager? = null
    var sliderLayout : SliderLayout? = null

    override fun populatePage(currentMeh : Meh) {
        titleView?.setText(currentMeh.deal.title)
        storyView?.setText(currentMeh.deal.story.getFormattedBody())
        rootView?.setBackgroundColor(currentMeh.deal.theme.getParsedBackgroundColor())
        currentMeh.deal.photos.forEach {
            val sliderView = DefaultSliderView(this)
            sliderView.image(it)
            sliderLayout?.addSlider(sliderView)
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        getLayout()
        presenter.getCurrentMeh()
    }

    private fun getLayout() {
        rootView = scrollView {
            verticalLayout {
                padding = dip(16)

                sliderLayout = sliderLayout {

                }.layoutParams(width = matchParent, height = dip(300))

                titleView = textView("Hello") {
                    textSize = 24f
                }.layoutParams(width = matchParent, height = matchParent){
                    bottomMargin = dip(8)
                }

                storyView = textView("Story") {

                }

                button("Say Hello") {
                    onClick { toast("Hello, Test!") }
                }
            }
        }
    }

    fun ViewManager.sliderLayout(init: SliderLayout.() -> Unit = {}) =
            __dslAddView({SliderLayout(it)}, init, this)
}
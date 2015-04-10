package io.dwak.meh.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.bindView
import io.dwak.meh.presenter.MainPresenterImpl
import io.dwak.meh.R
import io.dwak.meh.base.BaseActivity
import io.dwak.meh.model.Meh

open class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {
    override fun setView() {
        getPresenter().view = this
    }

    override fun getPresenterClass() : Class<MainPresenterImpl> = javaClass()

    val titleView : TextView by bindView(R.id.title)
    val storyView : TextView by bindView(R.id.story)
    val rootView : View by bindView(R.id.root_view)

    override fun populatePage(currentMeh : Meh) {
        titleView.setText(currentMeh.deal.title)
        storyView.setText(currentMeh.deal.story.getFormattedBody())
        rootView.setBackgroundColor(currentMeh.deal.theme.getParsedBackgroundColor())
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPresenter().getCurrentMeh()
    }
}
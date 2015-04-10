package io.dwak.meh

import android.os.Bundle
import android.widget.TextView
import butterknife.bindView
import io.dwak.meh.model.CurrentMeh
import io.dwak.meh.network.MehService


open class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {
    override fun setView() {
        getPresenter().view = this
    }

    override fun getPresenterClass() : Class<MainPresenterImpl> = javaClass()

    val titleView : TextView by bindView(R.id.title)

    override fun populatePage(currentMeh : CurrentMeh) {
        titleView.setText(currentMeh.deal.title)
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super<BaseActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPresenter().getCurrentMeh()
    }
}

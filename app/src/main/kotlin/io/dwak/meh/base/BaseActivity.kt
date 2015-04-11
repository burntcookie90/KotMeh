package io.dwak.meh.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import kotlin.properties.Delegates

/**
 * Created by vishnu on 4/9/15.
 */
public abstract class BaseActivity<T : BasePresenter<*>> : ActionBarActivity() {
    val presenter : T by Delegates.lazy {
        getPresenterClass().newInstance()
    }

    abstract fun getPresenterClass() : Class<T>
    abstract fun setView()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        presenter
        setView()
    }
}
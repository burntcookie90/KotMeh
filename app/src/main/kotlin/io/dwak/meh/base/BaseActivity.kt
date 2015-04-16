package io.dwak.meh.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import kotlin.properties.Delegates

public abstract class BaseActivity<T : BasePresenter<*>> : ActionBarActivity() {
    val presenter : T by Delegates.lazy {
        presenterClass.newInstance()
    }

    abstract val presenterClass : Class<T>
    abstract fun setView()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
    }
}
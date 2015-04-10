package io.dwak.meh.base

import android.os.Bundle
import android.support.v7.app.ActionBarActivity

/**
 * Created by vishnu on 4/9/15.
 */
public abstract class BaseActivity<T : BasePresenter<*>> : ActionBarActivity() {
    private var mPresenter : T? = null
    protected fun getPresenter() : T {
        if (mPresenter == null) {
            try {
                mPresenter = getPresenterClass().newInstance()
            } catch (e : InstantiationException) {
                e.printStackTrace()
            } catch (e : IllegalAccessException) {
                e.printStackTrace()
            }

        }

        return mPresenter!!
    }

    abstract fun getPresenterClass() : Class<T>
    abstract fun setView()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter()
        setView()
    }
}
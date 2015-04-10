package io.dwak.meh.base

import kotlin.properties.Delegates

/**
 * Created by vishnu on 4/9/15.
 */
public open class BasePresenter<T> {
    var view : T by Delegates.notNull()
}
package io.dwak.meh.interactor

import io.dwak.meh.model.Meh
import rx.Observable

/**
 * Created by vishnu on 4/9/15.
 */
public trait CurrentMehInteractor {
    fun getCurrentMeh() : Observable<Meh>
}
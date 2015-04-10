package io.dwak.meh

import io.dwak.meh.model.CurrentMeh
import rx.Observable

/**
 * Created by vishnu on 4/9/15.
 */
public trait CurrentMehInteractor {
    fun getCurrentMeh() : Observable<CurrentMeh>
}
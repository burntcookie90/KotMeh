package io.dwak.meh.interactor

import io.dwak.meh.model.Meh
import rx.Observable

public trait CurrentMehInteractor {
    fun getCurrentMeh() : Observable<Meh>
}
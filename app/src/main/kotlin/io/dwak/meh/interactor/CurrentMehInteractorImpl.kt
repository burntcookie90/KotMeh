package io.dwak.meh.interactor

import io.dwak.meh.model.Meh
import io.dwak.meh.network.MehService
import retrofit.RestAdapter
import rx.Observable
import kotlin.properties.Delegates

public class CurrentMehInteractorImpl : CurrentMehInteractor {
    val mehService : MehService by Delegates.lazy {
        RestAdapter.Builder()
                .setEndpoint(MehService.ENDPOINT_URL + MehService.ENDPOINT_VERSION)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(javaClass<MehService>())
    }

    override fun getCurrentMeh() : Observable<Meh> = mehService.getCurrentMeh()
}
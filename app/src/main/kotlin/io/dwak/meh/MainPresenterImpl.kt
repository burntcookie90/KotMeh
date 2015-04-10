package io.dwak.meh

import io.dwak.meh.network.MehService
import retrofit.RestAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * Created by vishnu on 4/9/15.
 */
public class MainPresenterImpl : BasePresenter<MainView>, MainPresenter {
    private var currentMehInteractor : CurrentMehInteractor

    constructor() {
        currentMehInteractor = CurrentMehInteractorImpl()
    }

    override fun getCurrentMeh() {
        currentMehInteractor.getCurrentMeh()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action1 { view!!.populatePage(it) })
    }
}
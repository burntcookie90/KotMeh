package io.dwak.meh.presenter

import io.dwak.meh.base.BasePresenter
import io.dwak.meh.interactor.CurrentMehInteractor
import io.dwak.meh.interactor.CurrentMehInteractorImpl
import io.dwak.meh.model.Item
import io.dwak.meh.view.MainView
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

public class MainPresenterImpl : BasePresenter<MainView>, MainPresenter {
    private var currentMehInteractor : CurrentMehInteractor

    constructor() {
        currentMehInteractor = CurrentMehInteractorImpl()
    }

    override fun getCurrentMeh() {
        currentMehInteractor.getCurrentMeh()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Action1 {
                    view.populatePage(it)
                })
    }
}
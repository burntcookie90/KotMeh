package io.dwak.meh.presenter

import io.dwak.meh.base.BasePresenter
import io.dwak.meh.interactor.CurrentMehInteractor
import io.dwak.meh.interactor.CurrentMehInteractorImpl
import io.dwak.meh.model.Item
import io.dwak.meh.view.MainView
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
                .subscribe(Action1 {
                    it.deal.formattedPriceString = getBuyButtonText(it.deal.items)
                    view.populatePage(it)
                })
    }

    private fun getBuyButtonText(items : List<Item>) : String {
        var lowestPrice : Int = Int.MAX_VALUE
        var highestPrice : Int = Int.MIN_VALUE
        items.forEach {
            if (it.price > highestPrice) {
                highestPrice = it.price
            }
            if (it.price < lowestPrice) {
                lowestPrice = it.price
            }
        }

        var formattedPriceString : String
        formattedPriceString = if (lowestPrice == highestPrice) {
            "$${lowestPrice}"
        }
        else {
            "$${lowestPrice} - $${highestPrice}"
        }

        return formattedPriceString
    }
}
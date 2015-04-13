package io.dwak.meh.model

import kotlin.properties.Delegates

class Deal (
        val feautures : String,
        val id : String,
        val items : List<Item>,
        val photos : List<String>,
        val title : String,
        val soldOutAt : String,
        val specifications : String,
        val story : Story,
        val theme : Theme,
        val url : String,
        val topic : Topic) {

    val formattedPriceString : String by Delegates.lazy {
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

        if (lowestPrice == highestPrice) {
            "$${lowestPrice}"
        }
        else {
            "$${lowestPrice} - $${highestPrice}"
        }
    }

}
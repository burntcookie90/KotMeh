package io.dwak.meh.model

import android.text.Html
import android.text.Spanned
import com.commonsware.cwac.anddown.AndDown
import io.dwak.meh.toSpannedFromMarkdown

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

    fun getFormattedSpecifications() : Spanned = specifications.toSpannedFromMarkdown()

    fun getFormattedPriceString() : String {
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

        return if (lowestPrice == highestPrice) {
            "$${lowestPrice}"
        }
        else {
            "$${lowestPrice} - $${highestPrice}"
        }
    }

}

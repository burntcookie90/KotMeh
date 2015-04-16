package io.dwak.meh.view

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

public class ImagePagerAdapter(context : Context) : PagerAdapter(){
    private val context : Context;
    var imageUrls : List<String>? = null

    init {
       this.context = context
    }

    override fun instantiateItem(container : ViewGroup?, position : Int) : Any? {
        val imageView  = ImageView(context)
        if(imageUrls?.get(position) !== null){
            Picasso.with(context).load(imageUrls?.get(position)).into(imageView)
            (container as ViewPager).addView(imageView)
        }
        else {
            val blankView = View(context)
            (container as ViewPager).addView(blankView)
        }

        return imageView
    }

    override fun destroyItem(container : ViewGroup?, position : Int, o : Any?) = (container as ViewPager).removeView(o as View)

    override fun isViewFromObject(view : View?, o : Any?) : Boolean = view === o as View

    override fun getCount() : Int = imageUrls?.size() ?: 0
}
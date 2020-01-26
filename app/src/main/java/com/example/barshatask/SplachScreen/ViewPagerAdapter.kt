package com.example.barshatask.SplachScreen

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.viewpager.widget.PagerAdapter
import com.example.barshatask.R
import com.squareup.picasso.Picasso


class ViewPagerAdapter(var context: Context) : PagerAdapter() {
    private val images = arrayOf<Int>(R.drawable.background, R.drawable.background, R.drawable.background)
    var mCustomPosition:Int=0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
      return view==`object`
    }

    override fun getCount(): Int =images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
      var mImageView: ImageView = ImageView(context)
        if(mCustomPosition>2)
        { mCustomPosition=0}
       Picasso.get()
           .load(images[mCustomPosition])
           .fit()
           .centerCrop()
           .into(mImageView)
        container.addView(mImageView)
        return mImageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}
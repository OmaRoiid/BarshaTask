package com.example.barshatask.SplachScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

import kotlinx.android.synthetic.main.activity_splach_screen.*
import java.util.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.barshatask.LoginScreen.MainActivity
import com.example.barshatask.R


class SplachScreen : AppCompatActivity() {
    lateinit var mViewPagerAdapter:ViewPagerAdapter
    lateinit var mTimer: Timer
    lateinit var startMainActivity:Intent
    var currentImagePostition :Int=0
    var customPosition:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
        //navigate to the  Login Screen After 15 Sec
        Handler().postDelayed({
            startMainActivity= Intent(this@SplachScreen, MainActivity::class.java)
            this@SplachScreen.startActivity(startMainActivity)
            finish()
        }, 15000)
        mViewPagerAdapter= ViewPagerAdapter(this)
        vp_imageSlider.adapter=mViewPagerAdapter
        showDots(customPosition++)
        autoSlider()
        vp_imageSlider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if(customPosition>3)
                    customPosition=0
                showDots(customPosition++)
            }

        })

    }
    fun autoSlider()
    {
        val mHandler = Handler()
        val mRunnable= Runnable {
            if(currentImagePostition==2){
                currentImagePostition=0
                vp_imageSlider.setCurrentItem(currentImagePostition++,true)
            }
        }
        mTimer=Timer()
        val mTimerTask = object : TimerTask() {
            override fun run() {
                mHandler.post(mRunnable)
            }
        }
        mTimer.schedule(mTimerTask,250,2500)

    }
    fun showDots(currantSlidPostion :Int)
    {
        if (dotsContiner.childCount>0)
            dotsContiner.removeAllViews()

         val dotsImages = arrayOfNulls<ImageView>(3)
        for(i in 0 until 3){
            dotsImages[i]=ImageView(this)
            if(i==currantSlidPostion){
                dotsImages[i]!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots))
            }else{
                dotsImages[i]!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_dots))
            }
          var layoutPrams: LinearLayout.LayoutParams =LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutPrams.setMargins(8,0,8,0)
            dotsContiner.addView(dotsImages[i],layoutPrams)

        }

    }

}

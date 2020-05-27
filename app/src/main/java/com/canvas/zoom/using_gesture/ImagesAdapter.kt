package com.canvas.zoom.using_gesture

import android.content.Context
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.canvas.zoom.R
import com.github.chrisbanes.photoview.PhotoView


class ImagesAdapter(private val context: Context) :
    PagerAdapter() {
    private var ivPhotoGallery: PhotoView? = null
    private val imageList = ArrayList<String>(3)
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private lateinit var myZoomageView: AppCompatImageView

    init {
        imageList.add("https://images.unsplash.com/photo-1527168027773-0cc890c4f42e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1457369804613-52c61a468e7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val rootView = LayoutInflater.from(container.context).inflate(R.layout.adapter_photo_gallery, null, false)
        myZoomageView = rootView.findViewById(R.id.myZoomageView)
        myZoomageView.loadURL(imageList[position])
        mScaleGestureDetector = ScaleGestureDetector(myZoomageView.context, ScaleListener())
        rootView.setOnTouchListener(View.OnTouchListener { v, event ->
            mScaleGestureDetector!!.onTouchEvent(event)
            true
        })

        container.addView(rootView)
        return rootView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            myZoomageView.scaleX = mScaleFactor
            myZoomageView.scaleY = mScaleFactor
            return true
        }
    }
}

fun AppCompatImageView.loadURL(imageURL: String) {
    Glide.with(this).load(imageURL).into(this)
}
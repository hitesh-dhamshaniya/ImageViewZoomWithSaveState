package com.canvas.zoom.using_gesture

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.canvas.zoom.R
import kotlinx.android.synthetic.main.activity_scale_gesture_zoom.*


class ScaleGestureZoomActivity : AppCompatActivity() {
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private lateinit var mImageView: AppCompatImageView
    private val imageURL: String =
        "https://images.unsplash.com/photo-1527168027773-0cc890c4f42e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_gesture_zoom)
        setTitle("Using manual gesture detection")
        mImageView = findViewById(R.id.imgScaleGesture)

        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
        Glide.with(this).load(imageURL).into(mImageView)
        /*mImageView.onTouchEvent(touchListner)*/
        /*mImageView.post {
            mImageView.setOnTouchListener(OnTouchListener { v, event ->
                mScaleGestureDetector!!.onTouchEvent(event)
                true
            })
        }*/
        btnSetViewPager.setOnClickListener {
            viewPager.adapter = FragmentAdapter(supportFragmentManager)
        }
    }

    val touchListner = View.OnTouchListener { v, event -> true }

    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        mScaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            mImageView.scaleX = mScaleFactor
            mImageView.scaleY = mScaleFactor
            return true
        }
    }
}

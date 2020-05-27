package com.canvas.zoom.using_gesture

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.canvas.zoom.R


class ScaleGestureZoomFragment : Fragment() {
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private lateinit var mImageView: AppCompatImageView

    companion object {
        fun newInstance(): ScaleGestureZoomFragment {
            return ScaleGestureZoomFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mRootView = inflater.inflate(R.layout.fragment_scale_gesture_zoom, container, false)
        mImageView = mRootView.findViewById(R.id.imgInFragment)
        mImageView.loadURL("https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        mScaleGestureDetector = ScaleGestureDetector(mRootView.context, ScaleListener())
        mRootView.setOnTouchListener { v, motionEvent ->
            mScaleGestureDetector!!.onTouchEvent(motionEvent)
            true
        }
        return mRootView
    }


    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            Log.e("onScale", "Factor ==> $mScaleFactor")
            if (mScaleFactor > 8 || mScaleFactor < 1) {
                return true
            }
            mImageView.scaleX = mScaleFactor
            mImageView.scaleY = mScaleFactor
            return true
        }
    }
}

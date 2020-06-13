package com.canvas.zoom.photoview

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canvas.zoom.R
import com.canvas.zoom.using_gesture.loadURL
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewFragment : Fragment() {

    private lateinit var iPhotoViewInFragment: PhotoView
    private var position = 0;

    companion object {
        const val KEY_EXTRA_IMAGE_URL = "extra.key.image"
        private val KEY_POSITION = "key.state.position"
        private val mScaleMap = HashMap<Int, Float>()

        fun newInstance(position: Int, imageURL: String) = PhotoViewFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EXTRA_IMAGE_URL, imageURL)
                putInt(KEY_POSITION, position)
                mScaleMap[position] = 1.0f
            }
        }

        fun addScale(position: Int, value: Float) {
            if (value < 1) return
            mScaleMap[position] = value
        }

        fun getScale(position: Int): Float {
            return mScaleMap[position] ?: 1.0f
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("onActivityCreated", "onActivityCreated ==> $savedInstanceState")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mRootView = inflater.inflate(R.layout.fragment_photoview, container, false)
        iPhotoViewInFragment = mRootView.findViewById(R.id.iPhotoViewInFragment)
        iPhotoViewInFragment.loadURL(arguments?.get(KEY_EXTRA_IMAGE_URL) as String)
        iPhotoViewInFragment.maximumScale = 8f;
        position = arguments?.getInt(KEY_POSITION)!!
        Handler().postDelayed({
            iPhotoViewInFragment.scale = getScale(position)
        }, 200)

        Handler().postDelayed({
            iPhotoViewInFragment.setOnMatrixChangeListener {
                addScale(position, iPhotoViewInFragment.scale)
                Log.e("Scale", "===> ${iPhotoViewInFragment!!.scale}")
            }
        }, 1500)
        return mRootView
    }
}
package com.canvas.zoom.photoview

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canvas.zoom.R
import com.canvas.zoom.using_gesture.loadURL
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewFragment : Fragment() {

    private var iPhotoViewInFragment: PhotoView? = null
    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0
    private var mScreenBase: Float = 2f // hypothetic not found reference so
    private var position = 0;

    companion object {
        const val KEY_EXTRA_IMAGE_URL = "extra.key.image"
        private val KEY_ZOOMLEVEL = "save.state.key.ZoomLevel"
        private val KEY_POSITION = "key.state.position"
        private val KEY_RECTF = "save.state.key.RectF"

        fun newInstance(position: Int, imageURL: String) = PhotoViewFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EXTRA_IMAGE_URL, imageURL)
                putInt(KEY_POSITION, position)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("onActivityCreated", "onActivityCreated ==> $savedInstanceState")
        /*savedInstanceState?.getFloat(KEY_ZOOMLEVEL)?.let {
            iPhotoViewInFragment!!.post {
                iPhotoViewInFragment!!.scale = it
                iPhotoViewInFragment!!.maximumScale = 8f;
            }
        }*/
        if (iPhotoViewInFragment != null) {
            val scaleValue = DataHolder.get(position)
            Handler().postDelayed({
                iPhotoViewInFragment!!.setScale(scaleValue!!, true)
            }, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (iPhotoViewInFragment != null) {
            val theMatrix: Matrix = iPhotoViewInFragment!!.matrix!!
            val theFloat = FloatArray(9)
            theMatrix.getValues(theFloat)
            val theRect: RectF? = iPhotoViewInFragment!!.displayRect
            if (theRect != null) {
                if (theRect.left > (mViewWidth / 2) || (theRect.left >= 0)) {
                    theRect.left = 0f;
                } else {
                    theRect.left = (theRect.left - (mViewWidth / 2)) / mScreenBase;
                }
                if (theRect.top > (mViewHeight / 2) || (theRect.top >= 0)) {
                    theRect.top = 0f;
                } else {
                    theRect.top = (theRect.top - (mViewHeight / 2)) / mScreenBase;

                }
                outState.putFloat(KEY_ZOOMLEVEL, iPhotoViewInFragment!!.scale);
                outState.putParcelable(KEY_RECTF, theRect);
            } else {
                Log.e("onSaveInstanceState", "onSaveInstanceState ==> $theRect")
            }
        } else {
            Log.e("onSaveInstanceState", "onSaveInstanceState ==> $iPhotoViewInFragment")
        }
    }*/

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /*if (iPhotoViewInFragment != null) {
            iPhotoViewInFragment!!.maximumScale = 8f;
            outState.putFloat(KEY_ZOOMLEVEL, iPhotoViewInFragment!!.scale);
        } else {
            Log.e("onSaveInstanceState", "onSaveInstanceState ==> $iPhotoViewInFragment")
        }*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mRootView = inflater.inflate(R.layout.fragment_photoview, container, false)
        iPhotoViewInFragment = mRootView.findViewById(R.id.iPhotoViewInFragment)
        iPhotoViewInFragment!!.loadURL(arguments?.get(KEY_EXTRA_IMAGE_URL) as String)
        iPhotoViewInFragment!!.maximumScale = 8f;
        position = arguments?.getInt(KEY_POSITION)!!
        Handler().postDelayed({
            iPhotoViewInFragment!!.scale = DataHolder.get(position)!!
        }, 100)

        Handler().postDelayed({
            iPhotoViewInFragment!!.setOnMatrixChangeListener {
                DataHolder.add(position, iPhotoViewInFragment!!.scale)
                Log.e("Scale", "===> ${iPhotoViewInFragment!!.scale}")
            }
        }, 1500)
        return mRootView
    }
}
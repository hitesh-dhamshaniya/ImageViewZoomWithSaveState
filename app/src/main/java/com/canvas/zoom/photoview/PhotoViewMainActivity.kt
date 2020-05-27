package com.canvas.zoom.photoview

import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.canvas.zoom.R
import kotlinx.android.synthetic.main.activity_photo_view_main.*
import kotlin.math.abs


class PhotoViewMainActivity : AppCompatActivity() {

    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0
    private val imageURL: String =
        "https://images.unsplash.com/photo-1590405219255-39ad34b8ad44?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1051&q=80"
    private val KEY = "save_scale_state_key"
    private var imageScale: Float = 0.0f;
    private val KEY_ZOOMLEVEL = "save.state.key.ZoomLevel"
    private val KEY_RECTF = "save.state.key.RectF"
    private val mBaseMatrixValues: FloatArray = FloatArray(9)
    private var mScreenBase: Float = 2f // hypothetic not found reference so
    private var theMaxLeftValue: Float = 0f;
    private var theMaxTopValue: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view_main)
        mPhotoView.maximumScale = 6f;
        Glide.with(this).load(imageURL).into(mPhotoView)
        //setupTreeObservable(savedInstanceState)
        savedInstanceState?.getFloat(KEY_ZOOMLEVEL)?.let {
            mPhotoView.post {
                mPhotoView.scale = it
            }
        }
    }

    private fun setupTreeObservable(savedInstanceState: Bundle?) {
        mPhotoView.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                mPhotoView.viewTreeObserver.removeOnPreDrawListener(this)
                savedInstanceState?.getFloat(KEY_ZOOMLEVEL)?.let {
                    mPhotoView.post {
                        mPhotoView.setScale(it)
                    }
                }
                /* mViewHeight = mPhotoView.measuredHeight
                 mViewWidth = mPhotoView.measuredWidth
                 val theMatrix: Matrix = mPhotoView.matrix
                 theMatrix.getValues(mBaseMatrixValues);

                 if(mBaseMatrixValues.isEmpty()) return true

                 mScreenBase = mBaseMatrixValues[0];
                 val theWidth = mPhotoView.width
                 if (savedInstanceState != null) {
                     var theFloats: FloatArray = FloatArray(9)
                     val theZoom: Float = savedInstanceState.getFloat(KEY_ZOOMLEVEL)
                     val theRect: RectF = savedInstanceState.getParcelable(KEY_RECTF)!!
                     theFloats[0] = theZoom;
                     theFloats[4] = theZoom;
                     theFloats[2] = (theRect.left * mScreenBase) - (theZoom * mBaseMatrixValues[2]) + (mViewWidth / 2); //Left
                     theFloats[5] = (theRect.top * mScreenBase) - (theZoom * mBaseMatrixValues[5]) + (mViewHeight / 2) //Top
                     theFloats[8] = 1.0.toFloat()
                     theFloats = CheckBoundaries(theZoom, theFloats, theRect)!!;
                     theMatrix.setValues(theFloats);
                     mPhotoView.setScale(theZoom, false)
                     mPhotoView.setDisplayMatrix(theMatrix); //Sets the mSuppMatrix in the PhotoViewAttacher
                     val theImageViewMatrix: Matrix = mPhotoView.matrix //Gets the new mDrawMatrix
                     mPhotoView.imageMatrix = theImageViewMatrix
                 }*/
                return true
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val theMatrix: Matrix = mPhotoView.matrix!!
        val theFloat = FloatArray(9)
        theMatrix.getValues(theFloat)
        val theRect: RectF? = mPhotoView.displayRect
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

            outState.putFloat(KEY_ZOOMLEVEL, mPhotoView.scale);
            outState.putParcelable(KEY_RECTF, theRect);
        }

        outState.putFloat(KEY_ZOOMLEVEL, mPhotoView.scale);

    }

    private fun CheckBoundaries(aZoom: Float, aFloats: FloatArray, aRect: RectF): FloatArray? {
        if (aZoom.toDouble() == 1.0) //If the zoom is all the way out
        {
            aFloats[2] = 0f
            aFloats[5] = 0f
            return aFloats
        }
        theMaxLeftValue = mViewHeight * aZoom - mViewWidth + aZoom * mBaseMatrixValues[2]
        theMaxTopValue = mViewWidth * aZoom - mViewHeight + aZoom * mBaseMatrixValues[5]
        if (Math.abs(aFloats[2]) > theMaxLeftValue) {
            aFloats[2] = -Math.abs(theMaxLeftValue) + 10
        } else if (abs(aFloats[2]) < aZoom * mBaseMatrixValues[2]) {
            aFloats[2] = -(aZoom * mBaseMatrixValues[2])
        }
        if (abs(aFloats[5]) > theMaxTopValue) {
            aFloats[5] = -Math.abs(theMaxTopValue) + 10
        } else if (abs(aFloats[5]) < aZoom * mBaseMatrixValues[5]) {
            aFloats[5] = -(aZoom * mBaseMatrixValues[5])
        }
        if (aFloats[2] > 0) aFloats[2] = (-(mViewWidth / 2)).toFloat() else if (aFloats[5] > 0) aFloats[5] = (-(mViewHeight / 2)).toFloat()
        return aFloats
    }

    /*
    First apporch to solve problem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view_main)

        if (savedInstanceState == null) {
            Log.e("onCreate", " saveInstanseState is null")
        } else {
            imageScale = savedInstanceState.getFloat(KEY)
        }
        initUI()
    }

    private fun initUI() {
        Glide.with(this).load(imageURL).into(mPhotoView)
        mPhotoView.setOnMatrixChangeListener(object : OnMatrixChangedListener {
            override fun onMatrixChanged(rect: RectF?) {
                imageScale = imgZoom.scale
            }
        })
        imgZoom.setScale(3.0f, true)
        if (imageScale == 0f) {
            //do nothing
        } else {
            mPhotoView.scale = imageScale
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putFloat(KEY, imageScale)
        super.onSaveInstanceState(outState)
    }*/
}

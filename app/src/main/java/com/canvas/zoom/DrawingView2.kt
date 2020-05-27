package com.canvas.zoom

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

class DrawingView2 : View {

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0
    ) :
            super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int
    ) :
            super(context, attrs, defStyleAttr, defStyleRes)

    private var mBitmap: Bitmap? = null
    private var mImageBitmap: Bitmap? = null
    private var mImageBitmapMatrix: Matrix? = null
    private var mCanvas: Canvas? = null

    private val mPath: Path = Path()
    private var mX: Float = 0f
    private var mY: Float = 0f

    private var mPaint: Paint = Paint()

    private var mW = 100
    private var mH = 100


    private var mZoomFactor: Float = 1f
    private var mZoomCenterX = -1.0f
    private var mZoomCenterY = -1.0f
    private var mMinZoomFactor = 1.0f
    private val mMaxZoomFactor = 2.8f
    private val mScaleGestureDetector: ScaleGestureDetector
    private var mCanvasClipBounds: Rect = Rect()

    init {
        mPaint.color = Color.BLUE

        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 36f

        mScaleGestureDetector = ScaleGestureDetector(context, ScaleGestureListener())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (w <= 0 || h <= 0) return

        mW = w
        mH = h

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        mCanvas = Canvas(mBitmap!!)
        mCanvas?.drawColor(Color.TRANSPARENT)

        mImageBitmap?.let {
            mImageBitmapMatrix = Matrix().apply {
                setRectToRect(
                        RectF(
                                0f, 0f,
                                it.width.toFloat(),
                                it.height.toFloat()
                        ),
                        RectF(0f, 0f, mW.toFloat(), mH.toFloat()), Matrix.ScaleToFit.CENTER
                )
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.scale(mZoomFactor, mZoomFactor, mZoomCenterX, mZoomCenterY)

        canvas.drawColor(Color.WHITE)

        mImageBitmap?.let {
            canvas.drawBitmap(it, mImageBitmapMatrix!!, null)
        }

        canvas.drawBitmap(mBitmap!!, 0f, 0f, null)

        mCanvas?.drawPath(mPath, mPaint)

        canvas.getClipBounds(mCanvasClipBounds)

        canvas.restore()
    }

    private fun touchStart(x: Float, y: Float) {
        Log.d("touch_start", "$x,$y")
        // kill this so we don't double draw
        mPath.moveTo(x, y)

        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        if (mMultiTouch) return

        Log.d("touch_move", "$x,$y")
        mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
        mX = x
        mY = y
    }

    private fun touch_up() {
        if (mMultiTouch) return

        mPath.lineTo(mX, mY)

        mCanvas?.drawPath(mPath, mPaint)
        mPath.reset()
    }


    private var mMultiTouch = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        mScaleGestureDetector.onTouchEvent(event)

        val x = event.x / mZoomFactor + (mCanvasClipBounds.left ?: 0)
        val y = event.y / mZoomFactor + (mCanvasClipBounds.top ?: 0)

        if (event.pointerCount > 1) {
            mMultiTouch = true
            return true
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touch_up()
                invalidate()
                mMultiTouch = false
            }
        }
        return true
    }

    fun setImageBitmap(image: Bitmap) {
        mImageBitmap = image
    }

    var lastFocusX: Float = 0.toFloat()
    var lastFocusY: Float = 0.toFloat()

    private inner class ScaleGestureListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val focusX = detector.focusX
            val focusY = detector.focusY

            //Edited after comment by chochim
            val focusShiftX = focusX - lastFocusX
            val focusShiftY = focusY - lastFocusY

            Log.e("Focus", "$focusX:$focusY")
            Log.e("FocusShift", "$focusShiftX:$focusShiftY")

            lastFocusX = focusX
            lastFocusY = focusY

            mZoomFactor *= detector.scaleFactor
            mZoomFactor = Math.max(1f, Math.min(mZoomFactor, mMaxZoomFactor))
            mZoomFactor =
                    if (mZoomFactor > mMaxZoomFactor) mMaxZoomFactor else if (mZoomFactor < 1f) 1f else mZoomFactor

            //TODO: Replace (focusX + focusShiftX) with focusX
            mZoomCenterX =
                    (focusX - focusShiftX * mZoomFactor) / mZoomFactor + (mCanvasClipBounds.left
                            ?: 0)
            mZoomCenterY =
                    (focusY - focusShiftY * mZoomFactor) / mZoomFactor + (mCanvasClipBounds.top
                            ?: 0)

            invalidate()
            return true
        }
    }

}
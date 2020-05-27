package com.canvas.zoom.second

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.canvas.zoom.R
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        setupDrawView()
    }

    private fun setDrawViewScale(drawView: View, bitmap: Bitmap) {
        val width = drawView.width
        val height = drawView.height

        val imageWidth = bitmap.width
        val imageHeight = bitmap.height

        val params = if (drawView.layoutParams is FrameLayout.LayoutParams)
            drawView.layoutParams as FrameLayout.LayoutParams
        else drawView.layoutParams as RelativeLayout.LayoutParams
        val viewAspectRatio = width.toFloat() / height

        val aspectRatio = imageWidth.toFloat() / imageHeight
        if (aspectRatio.isNaN()) return

        if (viewAspectRatio < aspectRatio) {
            params.height = (width / aspectRatio).toInt()
        } else {
            params.width = (aspectRatio * height).toInt()
        }
        drawView.layoutParams = params
    }

    private fun setupDrawView() {
        val stream = assets.open("img_lls_book1_page1.jpg")
        val image = BitmapFactory.decodeStream(stream)
        drawView2.post {
            setDrawViewScale(drawView2, image)
        }
        drawView2.post {
            drawView2.setImageBitmap(image)
        }
    }
}
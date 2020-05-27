package com.canvas.zoom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.canvas.zoom.photoview.PhotoViewMainActivity
import com.canvas.zoom.photoview.PhotoViewWithViewPagerActivity
import com.canvas.zoom.photoview.ZoomageActivity
import com.canvas.zoom.using_gesture.ScaleGestureZoomActivity
import kotlinx.android.synthetic.main.activity_option_list.*

class OptionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_list)

        btnPhotoView.setOnClickListener {
            launchActivity(PhotoViewWithViewPagerActivity::class.java)
        }

        btnScaleGesture.setOnClickListener {
            launchActivity(ScaleGestureZoomActivity::class.java)
        }

        btnZoomage.setOnClickListener {
            launchActivity(ZoomageActivity::class.java)
        }

        btnPhotoViewActivity.setOnClickListener {
            launchActivity(PhotoViewMainActivity::class.java)
        }
    }

    fun launchActivity(clazz: Class<out Activity>) {
        startActivity(Intent(this, clazz))
    }
}

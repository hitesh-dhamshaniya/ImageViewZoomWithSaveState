package com.canvas.zoom.photoview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.canvas.zoom.R
import kotlinx.android.synthetic.main.activity_my_photoview.*

class PhotoViewWithViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_photoview)

        title = "Keep Zoom inside view pager"
        viewPagerPhotoView.adapter = PhotoViewPageAdapter(supportFragmentManager)
        viewPagerPhotoView.offscreenPageLimit = 5
    }
}

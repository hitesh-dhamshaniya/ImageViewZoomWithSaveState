package com.canvas.zoom.photoview

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.canvas.zoom.R
import kotlinx.android.synthetic.main.activity_zoomage.*

class ZoomageActivity : AppCompatActivity() {
    private val imageURL: String =
        "https://images.unsplash.com/photo-1590273223683-62d186b37a0d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoomage)
        setTitle("Using zoomage view")
        initUI()
    }

    private fun initUI() {
        Glide.with(this).load(imageURL).into(myZoomageView)
        val scaleFactor = myZoomageView.currentScaleFactor

        Log.e("Image", "==> $scaleFactor")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("onConfigurationChanged", "==> ${newConfig.toString()}")
        val scaleFactor = myZoomageView.currentScaleFactor
        Log.e("onConfigurationChanged ", "onConfigurationChanged ==> $scaleFactor")
    }
}

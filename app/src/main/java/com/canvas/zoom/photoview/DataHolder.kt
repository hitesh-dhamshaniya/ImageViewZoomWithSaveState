package com.canvas.zoom.photoview

import android.util.Log

object DataHolder {
    private val photoViewMap = HashMap<Int, Float>()

    init {
        add(0, 1.0f)
        add(1, 1.0f)
        add(2, 1.0f)
        add(3, 1.0f)
        add(4, 1.0f)
        add(5, 1.0f)
        add(6, 1.0f)
        add(7, 1.0f)
        add(8, 1.0f)
        add(9, 1.0f)
        add(10, 1.0f)
        Log.e("DataHolder", "==> Init called")
    }

    fun add(position: Int, zoomLevel: Float) {
        photoViewMap[position] = zoomLevel
    }

    fun get(position: Int): Float? {
        return photoViewMap[position]
    }
}
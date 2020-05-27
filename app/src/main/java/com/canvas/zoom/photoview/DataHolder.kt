package com.canvas.zoom.photoview

object DataHolder {
    private val photoViewMap = HashMap<Int, Float>()

    init {
        add(0, 1.0f)
        add(1, 1.0f)
        add(2, 1.0f)
        add(3, 1.0f)
    }

    fun add(position: Int, zoomLevel: Float) {
        photoViewMap[position] = zoomLevel
    }

    fun get(position: Int): Float? {
        return photoViewMap[position]
    }
}
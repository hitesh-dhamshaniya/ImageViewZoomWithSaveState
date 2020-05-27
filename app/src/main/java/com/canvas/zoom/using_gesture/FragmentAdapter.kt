package com.canvas.zoom.using_gesture

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val imageList = ArrayList<String>()

    init {
        imageList.add("https://images.unsplash.com/photo-1527168027773-0cc890c4f42e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1457369804613-52c61a468e7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
    }

    override fun getItem(position: Int) = ScaleGestureZoomFragment()

    override fun getCount() = imageList.size
}
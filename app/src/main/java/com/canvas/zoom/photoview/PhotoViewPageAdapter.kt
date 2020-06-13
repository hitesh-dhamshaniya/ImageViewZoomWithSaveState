package com.canvas.zoom.photoview

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PhotoViewPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val imageList = ArrayList<String>()

    init {
        imageList.add("https://images.unsplash.com/photo-1527168027773-0cc890c4f42e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1457369804613-52c61a468e7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1582090096769-c1c5278ea8ce?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1552959988-b94b76838365?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1563750272824-52e87a016c9b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=376&q=80")
        imageList.add("https://images.unsplash.com/photo-1548682586-39453d46a818?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=375&q=80")
        imageList.add("https://images.unsplash.com/photo-1586867845731-a51a944d0a7f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1049&q=80")
        imageList.add("https://images.unsplash.com/photo-1591911966549-8878913e5289?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80")
        imageList.add("https://images.unsplash.com/photo-1591869754715-5f679687039c?ixlib=rb-1.2.1&auto=format&fit=crop&w=658&q=80")
        imageList.add("https://images.unsplash.com/photo-1510130222742-710ba2997fc0?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80")
    }

    override fun getItem(position: Int) = PhotoViewFragment.newInstance(position, imageList[position])

    override fun getCount() = imageList.size


}
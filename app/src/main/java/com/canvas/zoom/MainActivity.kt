package com.canvas.zoom

import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.canvas.zoom.first.GraphView
import com.canvas.zoom.three.PinchZoomPan


class MainActivity : AppCompatActivity() {
    var mainDrawView: DrawView? = null
    var scroll_view: ScrollView? = null
    var h_scroll_view: HorizontalScrollView? = null
    var lin_layout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        lin_layout = LinearLayout(this);
        scroll_view = ScrollView(this);
        h_scroll_view = HorizontalScrollView(this);

        scroll_view!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        h_scroll_view!!.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )


        lin_layout!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )


        lin_layout!!.orientation = LinearLayout.VERTICAL;
        /*mainDrawView = DrawView(this)
        mainDrawView!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )*/

        val graphView = GraphView(this, null)
        val pinchZoomPan = PinchZoomPan(this, null)
        pinchZoomPan.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        /*scroll_view!!.addView(mainDrawView);*/
//        scroll_view!!.addView(graphView);
        pinchZoomPan.loadImageOnCanvas(Uri.parse(""))
        scroll_view!!.addView(pinchZoomPan);
        h_scroll_view!!.addView(scroll_view);

        setContentView(h_scroll_view);
    }
}

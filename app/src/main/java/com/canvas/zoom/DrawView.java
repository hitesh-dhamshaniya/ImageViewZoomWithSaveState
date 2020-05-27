package com.canvas.zoom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class DrawView extends View {

    Context app_context;

    public DrawView(Context context) {
        super(context);

        app_context = context;

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(metrics);
    }

// onMeasure must be included otherwise one or both scroll views will be compressed to zero pixels
// and the scrollview will then be invisible

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = 5000;
        int height = 5000 + 50; // Since 3000 is bottom of last Rect to be drawn added and 50 for padding.
        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.new_size_img);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

}
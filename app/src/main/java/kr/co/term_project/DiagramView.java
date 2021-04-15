package kr.co.term_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class DiagramView extends View {

    private Drawable image;
    private ScaleGestureDetector scaleGestureDetector;
    private float scale = 1.0f;

    public DiagramView(Context context) {
        super(context);
        image = ResourcesCompat.getDrawable(getResources(), R.drawable.completion_diagram, null);
        setFocusable(true);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale, scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();

            if (scale < 0.1f) {
                scale = 0.1f;
            }
            if (scale > 10.f) {
                scale = 10.0f;
            }

            invalidate();
            return true;
        }
    }

}

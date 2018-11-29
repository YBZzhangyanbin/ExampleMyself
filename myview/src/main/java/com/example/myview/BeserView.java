package com.example.myview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * 贝塞尔曲线
 */
public class BeserView extends View {

    private Path path;
    private Paint paint;
    Context context;
    private int width;
    private float offet;
    private int i;

    public BeserView(Context context) {
        this(context, null);
    }

    public BeserView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BeserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);


        path = new Path();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(-width+offet, 100+i/10);

        path.quadTo(-3 * width / 4 + offet, 200+i/10, -width / 2 + offet, 100+i/10);
        path.quadTo(-width / 4 + offet, 0+i/10, 0 + offet, 100+i/10);
        path.quadTo(width / 4 + offet, 200+i/10, width / 2 + offet, 100+i/10);
        path.quadTo(3 * width / 4 + offet, i/10, width + offet, 100+i/10);
        Log.i("zhangyb", "onDraw: " + i++);
        canvas.drawPath(path, paint);
    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, width);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(6000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offet = (float) animation.getAnimatedValue();

                postInvalidate();

            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


}

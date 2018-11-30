package com.example.myview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
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
    private int height;
    private Paint paint2;
    private PorterDuffXfermode mMode;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int defaulte = 600;
    private int padb;
    private int padt;
    private int padL;
    private int padR;

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
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStrokeWidth(5);
        paint2.setColor(Color.RED);
//        mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setColor(Color.parseColor("#88dddddd"));
        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        i++;
        //清除掉图像 不然path会重叠
        mBitmap.eraseColor(Color.parseColor("#00000000"));
//        canvas.drawCircle(500, 500, 250, paint2);
        path.reset();
        path.moveTo(-width + offet, width / 2 - i / 10);
        path.quadTo(-3 * width / 4 + offet, width / 2 + 100 - i / 10, -width / 2 + offet, width / 2 - i / 10);
        path.quadTo(-width / 4 + offet, width / 2 - 100 - i / 10, 0 + offet, width / 2 - i / 10);
        path.quadTo(width / 4 + offet, width / 2 + 100 - i / 10, width / 2 + offet, width / 2 - i / 10);
        path.quadTo(3 * width / 4 + offet, width / 2 - 100 - i / 10, width + offet, width / 2 - i / 10);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.close();
        mCanvas.drawCircle(width / 2, width / 2, width / 2 - padt, paint2);
        paint.setXfermode(mMode);
        //src
        mCanvas.drawPath(path, paint);
        paint.setXfermode(null);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, width);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(3000);
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
        padb = getPaddingBottom();
        padt = getPaddingTop();
        padL = getPaddingLeft();
        padR = getPaddingRight();
        width = w;
        height = h;
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getValue(widthMeasureSpec), getValue(heightMeasureSpec));
    }

    private int getValue(int widthMeasureSpec) {
        int spaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int spaceValue = MeasureSpec.getSize(widthMeasureSpec);
        switch (spaceMode) {
            case MeasureSpec.EXACTLY:
                return spaceValue;
            case MeasureSpec.AT_MOST:
                return Math.min(defaulte, spaceValue);
            case MeasureSpec.UNSPECIFIED:
                return defaulte;

        }
        return defaulte;
    }
}

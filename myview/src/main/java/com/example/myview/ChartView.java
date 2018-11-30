package com.example.myview;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class ChartView extends View {

    private Context context;
    private int w;
    private int h;
    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Path path;
    private float[] pos;
    private float[] tan;

    Integer[] angle = new Integer[]{18, 58, 78, 98, 98};
    Integer[] color_all = new Integer[]{Color.BLUE, Color.GRAY, Color.RED, Color.DKGRAY, Color.LTGRAY};
    private int angle1 = 360;
    private int moveAngle;
    boolean firstTouchAngle;


    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.GRAY);
        paint1.setAntiAlias(true);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.WHITE);
        paint2.setAntiAlias(true);

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.BLACK);
        paint3.setAntiAlias(true);

        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setColor(Color.BLUE);
        paint4.setAntiAlias(true);


        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4.setStyle(Paint.Style.FILL);
        paint4.setColor(Color.BLUE);
        paint4.setAntiAlias(true);
        //当前点的实际位置
        pos = new float[2];

        //当前点的tangent值
        tan = new float[2];


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        firstTouchAngle = false;

        int swapAngle = 0;
        for (int i = 0; i < angle.length; i++) {
            path.reset();
            canvas.save();
            canvas.rotate(swapAngle + (angle[i] / 2), 550, 550);
            paint1.setColor(color_all[i]);
            if (((swapAngle + angle[i] / 2) * 35 / 36) > angle1) {

                if (moveAngle > swapAngle && moveAngle < swapAngle + angle[i]) {
                    canvas.translate(50, 0);
                    canvas.drawArc(300, 300, 800, 800, -angle[i] / 2, angle[i], true, paint1);
                    path.moveTo(800, 550);
                    path.lineTo(900, 550);
                    canvas.drawPath(path, paint);
                    canvas.rotate(-(swapAngle + angle[i] / 2), 900, 550);
                    if (swapAngle + angle[i] / 2 > 90 && swapAngle + angle[i] / 2 < 270) {
                        canvas.drawLine(900, 550, 900 - 100, 550, paint);
                    } else {
                        canvas.drawLine(900, 550, 900 + 100, 550, paint);
                    }
                } else {
                    canvas.drawArc(300, 300, 800, 800, -angle[i] / 2, angle[i], true, paint1);
                    path.moveTo(800, 550);
                    path.lineTo(900, 550);
                    canvas.drawPath(path, paint);
                    canvas.rotate(-(swapAngle + angle[i] / 2), 900, 550);
                    if (swapAngle + angle[i] / 2 > 90 && swapAngle + angle[i] / 2 < 270) {
                        canvas.drawLine(900, 550, 900 - 100, 550, paint);
                    } else {
                        canvas.drawLine(900, 550, 900 + 100, 550, paint);
                    }
                }
            }
            canvas.restore();
            swapAngle = angle[i] + swapAngle + 2;


        }
        canvas.drawArc(300, 300, 800, 800, 0, angle1, true, paint2);


    }

    public void statrtAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(360, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle1 = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(3000);
        animator.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        moveAngle = getRotationBetweenLines(550, 550, event.getX(), event.getY());

        Log.i("zhangyb", "onTouchEvent: ==" + moveAngle);
        if (Math.pow(event.getX() - 550, 2) + Math.pow(event.getY() - 550, 2) > Math.pow(250, 2)) {
            moveAngle = -1;
        }

        invalidate();
        return super.onTouchEvent(event);

    }

    public int getRotationBetweenLines(float centerX, float centerY, float xInView, float yInView) {
        double rotation = 0;

        double k1 = (double) (centerY - centerY) / (centerX * 2 - centerX);
        double k2 = (double) (yInView - centerY) / (xInView - centerX);
        double tmpDegree = Math.atan((Math.abs(k1 - k2)) / (1 + k1 * k2)) / Math.PI * 180;

        Log.i("zhangyb", "getRotationBetweenLines: ====" + tmpDegree);

        if (xInView > centerX && yInView < centerY) {  //第一象限
            rotation = 360 - tmpDegree;
        } else if (xInView > centerX && yInView > centerY) //第二象限
        {
            rotation = tmpDegree;
        } else if (xInView < centerX && yInView > centerY) { //第三象限
            rotation = 180 - tmpDegree;
        } else if (xInView < centerX && yInView < centerY) { //第四象限
            rotation = 180 + tmpDegree;
        } else if (xInView == centerX && yInView < centerY) {
            rotation = 270;
        } else if (xInView == centerX && yInView > centerY) {
            rotation = 90;
        }

        return (int) rotation;
    }

}

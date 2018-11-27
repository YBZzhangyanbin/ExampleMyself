package com.example.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AntAnimation extends View {

    private Paint bigPaint;
    private Paint midPaint;
    private Paint textPaint;
    private Paint textCreditPaint;

    String[] sesameStr = new String[]{
            "350", "较差",
            "550", "中等",
            "600", "良好",
            "650", "优秀",
            "700", "极好",
            "950"
    };
    private Paint textMinPaint;
    private Bitmap bitmap;
    private Paint circlePaint;

    public AntAnimation(Context context) {
        this(context, null);
    }

    public AntAnimation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AntAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bigPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bigPaint.setStyle(Paint.Style.STROKE); //实线
        bigPaint.setStrokeWidth(8);
        bigPaint.setColor(Color.RED);
        bigPaint.setAlpha(80);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.STROKE); //实线
        circlePaint.setStrokeWidth(8);
        circlePaint.setColor(Color.RED);


        //中间宽带圆环
        midPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        midPaint.setStyle(Paint.Style.STROKE);
        midPaint.setStrokeWidth(30);
        midPaint.setColor(Color.RED);
        midPaint.setAlpha(80);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(30);
        textPaint.setStrokeWidth(8);
        textPaint.setColor(Color.RED);
        textPaint.setTextAlign(Paint.Align.CENTER);


        textCreditPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textCreditPaint.setStyle(Paint.Style.FILL);
        textCreditPaint.setTextSize(15);
        textCreditPaint.setColor(Color.RED);
        textCreditPaint.setTextAlign(Paint.Align.CENTER);

        textMinPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textMinPaint.setStyle(Paint.Style.FILL);
        textMinPaint.setStrokeWidth(1);
        textMinPaint.setColor(Color.WHITE);
        textMinPaint.setAlpha(130);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //初始化小圆点图片


        canvas.drawArc(30, 30, 530, 530, 165, 210, false, bigPaint);  //宽度是8px
        canvas.drawArc(70, 70, 490, 490, 165, 210, false, midPaint); //
        textPaint.setTextSize(150);
        canvas.drawText("500", 280, 280 + 30, textPaint);
        textPaint.setColor(Color.GREEN);
        canvas.drawPoint(280, 350, textPaint);
        canvas.save();
        canvas.rotate(-105, 280, 280);
        // 圆环的信用等级文本
        for (int i = 0; i < sesameStr.length; i++) {
            if (i % 2 == 1) {
                canvas.drawLine(280, 55, 280, 85, textPaint);
            }
            canvas.drawText(sesameStr[i], 280, 90 + 20, textCreditPaint);
            canvas.rotate(210f / (sesameStr.length - 1), 280, 280);
        }
        canvas.restore();
        canvas.save();

        canvas.rotate(-105, 280, 280);
        for (int i = 0; i < 100; i++) {
            if (i % 10 != 0) {
                canvas.drawLine(280, 70, 280, 85, textMinPaint);
            }
            if (i % 20 == 0 && i != 0) {
                textMinPaint.setStrokeWidth(8);
                canvas.drawLine(280, 45, 280, 85, textMinPaint);
                textMinPaint.setStrokeWidth(1);
            }
            canvas.rotate(210f / 100, 280, 280);
        }
        canvas.restore();
        canvas.save();

        drawRingProgress(canvas);


    }


    private void drawRingProgress(Canvas canvas) {

        //当前点的实际位置
        float[] pos = new float[2];

        //当前点的tangent值
        float[] tan = new float[2];
        Matrix matrix = new Matrix();
        matrix.reset();


        Path path = new Path();
        path.addArc(30, 30, 530, 530, 165, 100);
        PathMeasure measure = new PathMeasure(path, false);
        measure.getPosTan(measure.getLength() * 2f, pos, tan);
//        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);
        canvas.drawPath(path, circlePaint);
        circlePaint.setStrokeWidth(16);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.save();
        canvas.rotate(3, 280, 280);
        canvas.drawPoint(pos[0], pos[1], circlePaint);
        circlePaint.setStrokeWidth(8);
        canvas.restore();


    }
}

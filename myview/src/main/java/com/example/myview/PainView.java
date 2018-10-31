package com.example.myview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PainView extends View {

    private Paint paint;

    public PainView(Context context) {
        super(context, null);

    }

    public PainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public PainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.RED);
        for (int i = 0; i < 5; i++) {
            canvas.drawRect(100 + 70 * i, 100, 150 + 70 * i, 200 + 40 * i, paint);


        }
        for (int i = 0; i < 5; i++) {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawLine(125 + 70 * i, 100, 125 + 70 * i, 120, paint);

        }

        for (int i = 0; i < 5; i++) {
            int startX = 100 + 70 * i;


            for (int j = 0; j < 7; j++) {
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(2);

                canvas.drawLine(startX + 10 * j, 100, startX + 10 * j, 110, paint);

            }
        }

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawLine(100, 100, 600, 100, paint);

        canvas.drawLine(600, 85, 600, 115, paint);
        Path path = new Path();
        path.moveTo(600, 85);
        path.lineTo(600, 115);
        path.lineTo(630, 100);
//        path.close();
        canvas.drawPath(path, paint);
    }
}

package com.example.antmoneyanimation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CreditMumber extends View {
    public CreditMumber(Context context) {
   this(context,null);

    }

    public CreditMumber(Context context,  AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CreditMumber(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        //绘制画笔的绘制
        Paint bigCircle =new Paint(Paint.ANTI_ALIAS_FLAG);
        bigCircle.setAlpha(80);
        bigCircle.setColor(Color.WHITE);
        bigCircle.setStyle(Paint.Style.STROKE);
        bigCircle.setStrokeWidth(8);
    }
}

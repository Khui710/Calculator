package com.example.calculator.my_control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
自定义组件在应用程序中创建带有边框的标签
 */
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    Paint paint = new Paint();//声明一个Paint对象，用于绘制边框
    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {//实现构造方法MyTextView，其中调用了父类的构造方法
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {//重写onDraw方法，在绘制TextView时绘制边框。
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);//设置画笔的颜色为黑色。
        //绘制边框
        canvas.drawLine(0,0,this.getWidth(),0,paint);//绘制上方边框
        canvas.drawLine(0,this.getHeight(),this.getWidth(),this.getHeight()-1,paint);//绘制下方边框
        canvas.drawLine(0,0,0,this.getHeight(),paint);//绘制左边边框
        canvas.drawLine(this.getWidth()-1,0,this.getWidth()-1,this.getHeight(),paint);//绘制右边边框
    }
}

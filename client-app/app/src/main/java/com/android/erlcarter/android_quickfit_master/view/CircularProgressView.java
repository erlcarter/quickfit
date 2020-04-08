package com.android.erlcarter.android_quickfit_master.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.android.erlcarter.android_quickfit_master.R;

import org.w3c.dom.Text;

public class CircularProgressView extends View {
    /**
     *  画笔对象的引用
     */
    private Paint paint;
    /**
     * 圆环的颜色
     */
    private int roundColor;
    /**
     * 圆环进条的颜色
     */
    private int roundProgressColor;
    /**
     *  圆环顶部字体颜色
     */
    private int textTopColor;
    /**
     * 圆环中部字体颜色
     */
    private int textCenterColor;
    /**
     * 圆环底部字体颜色
     */
    private int textBottomColor;
    /**
     * 圆环顶部字体大小
     */
    private float textTopSize;
    /**
     * 圆环中部字体大小
     */
    private float textCenterSize;
    /**
     * 圆环底部字体大小
     */
    private float textBottomSize;
    /**
     * 是否显示圆环顶部内容
     */
    private boolean textTopIsDisplay;
    /**
     * 是否显示圆环中部内容
     */
    private boolean textCenterIsDisplay;
    /**
     * 是否显示圆环底部内容
     */
    private boolean textBottomIsDisplay;
    /**
     * 圆环的宽度
     */
    private float roundWidth;
    /**
     * 最大进度
     */
    private int max;
    /**
     * 当前进度
     */
    private int progress;
    /**
     * 目标进度
     */
    private int target;
    /**
     * 进度条风格：空心 | 实心
     */
    private int style;

    private static final int STROKE=0;
    private static final int FILL=1;

    public CircularProgressView(Context context) {
        this(context,null);
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircularProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);

        Paint paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressView);
        //获取我们自定义的属性
        roundColor = typedArray.getColor(R.styleable.CircularProgressView_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.CircularProgressView_roundProgressColor,Color.BLUE);
        textTopColor = typedArray.getColor(R.styleable.CircularProgressView_textTopColor,Color.GRAY);
        textCenterColor = typedArray.getColor(R.styleable.CircularProgressView_textCenterColor,Color.BLACK);
        textBottomColor = typedArray.getColor(R.styleable.CircularProgressView_textBottomColor,Color.GRAY);
        textTopSize = typedArray.getDimension(R.styleable.CircularProgressView_textTopSize,5);
        textCenterSize = typedArray.getDimension(R.styleable.CircularProgressView_textCenterSize,15);
        textBottomSize = typedArray.getDimension(R.styleable.CircularProgressView_textBottomSize,5);
        textTopIsDisplay = typedArray.getBoolean(R.styleable.CircularProgressView_textTopIsDisplay,true);
        textCenterIsDisplay = typedArray.getBoolean(R.styleable.CircularProgressView_textCenterIsDisplay,true);
        textBottomIsDisplay = typedArray.getBoolean(R.styleable.CircularProgressView_textBottomIsDisplay,true);
        roundWidth = typedArray.getDimension(R.styleable.CircularProgressView_roundWidth,5);
        max = typedArray.getInteger(R.styleable.CircularProgressView_max,100);
        style = typedArray.getInt(R.styleable.CircularProgressView_style,0);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的圆环
         */
        int circularCenter = getWidth() / 2;//获取圆心的x坐标
        int radius = (int) (circularCenter - roundWidth / 2);//获取圆环半径
        paint.setColor(roundColor);//设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE);//设置空心
        paint.setStrokeWidth(roundWidth);//设置圆环的宽度
        paint.setAntiAlias(true);//消除锯齿
        canvas.drawCircle(circularCenter,circularCenter,radius,paint);//画出圆环

        /**
         * 画里层的文字内容:从上到下绘制
         */
        //顶部
        paint.setStrokeWidth(0);
        paint.setColor(textTopColor);
        paint.setTextSize(textTopSize);
        paint.setTypeface(Typeface.DEFAULT); //设置字体
        String top = "已减去";
        float textTopWidth = paint.measureText(top);   //测量字体宽度，我们需要根据字体的宽度设置在中间
        if(textTopIsDisplay && style == STROKE){
            canvas.drawText(top, circularCenter - textTopWidth/2, circularCenter + textCenterColor/4, paint); //画出文字
        }
        //中部
        paint.setStrokeWidth(0);
        paint.setColor(textTopColor);
        paint.setTextSize(textTopSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
        int center = (int)(((float)progress));  //先转换成float在进行除法运算，不然都为0
        float textCenterWidth = paint.measureText(String.valueOf(center));   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        if(textTopIsDisplay && center != 0 && style == STROKE){
            canvas.drawText(String.valueOf(center), circularCenter - textCenterWidth/2, circularCenter + textCenterColor/2, paint); //画出文字
        }
        //底部
        paint.setStrokeWidth(0);
        paint.setColor(textTopColor);
        paint.setTextSize(textTopSize);
        paint.setTypeface(Typeface.DEFAULT); //设置字体
        int bottom = (int)((float)target);
        float textBottomWidth = paint.measureText("目标"+bottom);   //测量字体宽度，我们需要根据字体的宽度设置在中间
        if(textTopIsDisplay && style == STROKE){
            canvas.drawText("目标"+target, circularCenter - textBottomWidth/2, circularCenter - textCenterColor/4, paint); //画出文字
        }

        /**
         * 画圆弧进度条
         */
        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundProgressColor);  //设置进度的颜色
        RectF oval = new RectF(circularCenter - radius, circularCenter - radius, circularCenter
                + radius, circularCenter + radius);  //用于定义的圆弧的形状和大小的界限

        switch (style) {
            case STROKE:{
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 0, 360 * progress / max, false, paint);  //根据进度画圆弧
                break;
            }
            case FILL:{
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress !=0)
                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);  //根据进度画圆弧
                break;
            }
        }

    }
}

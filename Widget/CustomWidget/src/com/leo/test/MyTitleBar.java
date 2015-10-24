package com.leo.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by LT on 2015-05-14.
 */
public class MyTitleBar extends RelativeLayout{
    private Button leftButton;
    private Button rightButton;
    private TextView titleTextView;

    private String titleText;
    private float titleSize;
    private Drawable titleBackground;

    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;

    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;

    private LayoutParams leftLayoutParams,rightLayoutParams,titleLayoutParams;

    private TitleBarClickListener titleBarClickListener;


    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyTitleBar);
        titleText = ta.getString(R.styleable.MyTitleBar_titleText);
        titleSize = ta.getDimension(R.styleable.MyTitleBar_titleSize, 0);
        titleBackground = ta.getDrawable(R.styleable.MyTitleBar_titleBackground);

        leftText = ta.getString(R.styleable.MyTitleBar_leftText);
        leftTextColor = ta.getColor(R.styleable.MyTitleBar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.MyTitleBar_leftBackground);

        rightText = ta.getString(R.styleable.MyTitleBar_rightText);
        rightTextColor = ta.getColor(R.styleable.MyTitleBar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.MyTitleBar_rightBackground);

        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        titleTextView = new TextView(context);

        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);

        rightButton.setText(rightText);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);

        titleTextView.setText(titleText);
        titleTextView.setBackground(titleBackground);
        titleTextView.setTextSize(titleSize);

        setBackgroundColor(0xFFFFFFFF);
        setGravity(Gravity.CENTER);
        leftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftLayoutParams);

        rightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton, rightLayoutParams);

        titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(titleTextView, titleLayoutParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBarClickListener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBarClickListener.rightClick();
            }
        });
    }

    public interface TitleBarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setOnTitleBarClickListener(TitleBarClickListener titleBarClickListener){
        this.titleBarClickListener = titleBarClickListener;
    }
}

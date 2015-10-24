package com.leo.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] parent = {1000,2000,4000,8000,16000};
        vibrator.vibrate(parent,0);
        vibrator.vibrate(1000);
        TextView tv = (TextView) findViewById(R.id.text);
        AnimationDrawable animation = (AnimationDrawable)tv.getBackground();
        animation.start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv,"alpha",1f,0f);
        //vibrator.cancel();
    }
}

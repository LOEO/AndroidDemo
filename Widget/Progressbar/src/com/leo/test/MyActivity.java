package com.leo.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MyActivity extends Activity implements View.OnClickListener{

    private ProgressBar progressBar;
    private int num = 0;
    private Button add;
    private Button sub;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        setProgressBarVisibility(true);
        setProgress(num);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        /*final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(progressBar.getProgress() == 100){
                    timer.cancel();
                }
                progressBar.setProgress(progressBar.getProgress() + 1);
            }

        };


        timer.schedule(timerTask,3000,1000);*/
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add){
            progressBar.setProgress(progressBar.getProgress() + 10);
            setProgress(num+=1000);
        }else if(view.getId() == R.id.sub){
            progressBar.setProgress(progressBar.getProgress() - 10);
            setProgress(num-=1000);
        }
    }
}
